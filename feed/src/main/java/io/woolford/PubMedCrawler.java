package io.woolford;


import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.woolford.database.entity.DoctorRecord;
import io.woolford.database.entity.PubMedAbstractRecord;
import io.woolford.database.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
class PubMedCrawler {

    private static Logger logger = Logger.getLogger(PubMedCrawler.class.getName());

    @Value("${pubmed.request.interval.millis}")
    private long pubMedRequestIntervalMillis;

    @Value("${mongodb.enable}")
    private Boolean mongodbEnable;

    @Value("${kafka.enable}")
    private Boolean kafkaEnable;

    @Autowired
    DbMapper dbMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @PostConstruct
    private void crawlPubMed() throws IOException, IllegalAccessException, InstantiationException {

        for (DoctorRecord doctorRecord : dbMapper.getDoctorsRecordList()){

            MultiValueMap<String, String> esearchParams = new LinkedMultiValueMap<String, String>();
            esearchParams.add("db", "pubmed");
            esearchParams.add("retmode", "json");
            esearchParams.add("retmax", "1000");
            esearchParams.add("term", URLEncoder.encode(doctorRecord.getDoctorName()));

            // TODO: fix random issue where eutils.ncbi.nlm.nih.gov returns 'Unknown host'
            UriComponents esearchUriComponents = UriComponentsBuilder.fromHttpUrl("http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi").queryParams(esearchParams).build();
            URL esearchUrl = new URL(esearchUriComponents.toUriString());

            JsonNode esearchJsonNode = getJsonNodeFromUrlJson(esearchUrl);

            if (mongodbEnable){
                mongoTemplate.insert(esearchJsonNode.toString(), "doctor-name" );
            }

            //TODO: consider using Jolt instead
            ObjectMapper mapper = new ObjectMapper();
            String idListString = esearchJsonNode.get("esearchresult").get("idlist").toString();
            List<String> idList = mapper.readValue(idListString, new TypeReference<List<String>>(){});

            logger.info("idList: " + idList);

            for (String id : idList){

                MultiValueMap<String, String> efetchParams = new LinkedMultiValueMap<String, String>();
                efetchParams.add("db", "pubmed");
                efetchParams.add("retmode", "xml");
                efetchParams.add("rettype", "fasta");
                efetchParams.add("id", id);

                UriComponents efetchUriComponents = UriComponentsBuilder.fromHttpUrl("http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi").queryParams(efetchParams).build();
                URL efetchUrl = new URL(efetchUriComponents.toUriString());

                String efetchJson = getJsonNodeFromUrlXml(efetchUrl).toString();

                if (mongodbEnable){
                    mongoTemplate.insert(efetchJson, "pmid-search" );
                }

                if (kafkaEnable){
                    kafkaTemplate.send("pmid-search", efetchJson);
                }

                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("doc-abstract-jolt.json");

                List docAbstractJoltJson = JsonUtils.jsonToList(
                        new BufferedReader(new InputStreamReader(inputStream))
                                .lines()
                                .parallel()
                                .collect(Collectors.joining("\n"))
                );

                Chainr docAbstractJoltChainr = Chainr.fromSpec(docAbstractJoltJson);

                Object transformedOutput = docAbstractJoltChainr.transform(JsonUtils.jsonToMap(efetchJson));

                PubMedAbstractRecord pubMedAbstractRecord = mapper.convertValue(transformedOutput, PubMedAbstractRecord.class);

                Date createDate = new GregorianCalendar(pubMedAbstractRecord.getCreateYear(), pubMedAbstractRecord.getCreateMonth(), pubMedAbstractRecord.getCreateDay()).getTime();
                pubMedAbstractRecord.setCreateDate(createDate);

                dbMapper.insertPubMedAbstractRecord(pubMedAbstractRecord);

                logger.info(transformedOutput.toString());

            }

        }

    }

    private JsonNode getJsonNodeFromUrlJson(URL url) throws IOException {
        pubMedWait();
        String jsonString = getResponseForUrl(url);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(jsonString);
    }

    private JsonNode getJsonNodeFromUrlXml(URL url) throws IOException {
        pubMedWait();
        String xmlString = getResponseForUrl(url);
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readTree(xmlString);
    }

    private String getResponseForUrl(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        String redirect = connection.getHeaderField("Location");
        if (redirect != null){
            connection = new URL(redirect).openConnection();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String responseChunk;
        StringBuilder response = new StringBuilder();
        while ((responseChunk = bufferedReader.readLine()) != null) {
            response.append(responseChunk);
        }
        bufferedReader.close();

        return response.toString();
    }

    private void pubMedWait(){
        try {
            Thread.sleep(pubMedRequestIntervalMillis);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
