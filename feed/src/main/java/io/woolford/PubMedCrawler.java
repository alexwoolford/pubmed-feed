package io.woolford;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.woolford.database.entity.PubMedAbstractRecord;
import io.woolford.database.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

@Component
class PubMedCrawler {

    private static Logger logger = Logger.getLogger(PubMedCrawler.class.getName());

    @Value("${pubmed.request.interval.millis}")
    private long pubMedRequestIntervalMillis;

    @Autowired
    DbMapper dbMapper;

    @PostConstruct
    private void crawlPubMed() throws IOException, IllegalAccessException, InstantiationException {

        for (String doctorName : getDoctorNameArray()){

            MultiValueMap<String, String> esearchParams = new LinkedMultiValueMap<String, String>();
            esearchParams.add("db", "pubmed");
            esearchParams.add("retmode", "json");
            esearchParams.add("retmax", "1000");
            esearchParams.add("term", URLEncoder.encode(doctorName));

            UriComponents esearchUriComponents = UriComponentsBuilder.fromHttpUrl("http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi").queryParams(esearchParams).build();
            URL esearchUrl = new URL(esearchUriComponents.toUriString());

            JsonNode esearchJsonNode = getJsonNodeFromUrlJson(esearchUrl);

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

                JsonNode efetchJsonNode = getJsonNodeFromUrlXml(efetchUrl);

                // TODO: handle case where the JsonNode below is null.
                JsonNode citationJsonNode = efetchJsonNode.get("PubmedArticle").get("MedlineCitation");

                String title = String.valueOf(citationJsonNode.get("Article").get("ArticleTitle")).replaceAll("^\"|\"$", "");;
                String journalTitle = String.valueOf(citationJsonNode.get("Article").get("Journal").get("Title")).replaceAll("^\"|\"$", "");

                JsonNode docAbstractJsonNode = null;

                try {
                    // TODO: capture authors for each PubMed record in separate table
                    docAbstractJsonNode = citationJsonNode.get("Article").get("Abstract").get("AbstractText").get("");

                    JsonNode createDateJsonNode = citationJsonNode.get("DateCreated");
                    Date createDate = new GregorianCalendar(createDateJsonNode.get("Year").asInt(), createDateJsonNode.get("Month").asInt(), createDateJsonNode.get("Day").asInt()).getTime();

                    PubMedAbstractRecord pubMedAbstractRecord = new PubMedAbstractRecord();
                    pubMedAbstractRecord.setPmid(Integer.parseInt(id));
                    pubMedAbstractRecord.setTitle(title);
                    pubMedAbstractRecord.setJournalTitle(journalTitle);
                    pubMedAbstractRecord.setCreateDate(createDate);
                    pubMedAbstractRecord.setDocAbstract(String.valueOf(docAbstractJsonNode).replaceAll("^\"|\"$", ""));

                    dbMapper.insertPubMedAbstractRecord(pubMedAbstractRecord);
                    // TODO: capture deltas so it's not necessary to re-evaluate all the records

                } catch (Exception e) {
                    logger.log(java.util.logging.Level.WARNING, e.getMessage());
                }

                logger.info(doctorName + ": " + id + "; efetchUrl: " + efetchUrl + "; " + efetchJsonNode);

//                System.exit(0);

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

    private String[] getDoctorNameArray() throws IOException {
        String[] doctorNameArray = new String(Files.readAllBytes(Paths.get("feed/src/main/resources/doctor_names.txt"))).split("\n");
        return doctorNameArray;
    }

    private void pubMedWait(){
        try {
            Thread.sleep(pubMedRequestIntervalMillis);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
