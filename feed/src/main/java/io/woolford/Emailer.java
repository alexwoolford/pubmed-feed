package io.woolford;

import com.sendgrid.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.woolford.database.entity.PmidEmailRecord;
import io.woolford.database.entity.PubMedAbstractRecord;
import io.woolford.database.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class Emailer {

    private static Logger logger = Logger.getLogger(Emailer.class.getName());

    @Value("${sendgrid.api.key}")
    private String sendgridApiKey;

    @Value("${sendgrid.from}")
    private String sendgridFrom;

    @Autowired
    DbMapper dbMapper;

    @PostConstruct
    private void sendEmail() throws IOException, TemplateException {

        Configuration freemarkerConfig = new Configuration();
        freemarkerConfig.setDirectoryForTemplateLoading(new File("/Users/awoolford/pubmed-feed/feed/src/main/resources/"));
        Map<String, Object> freemarkerMap = new HashMap<>();

        freemarkerMap.put("pubmedAbtractRecordList", dbMapper.getPubMedAbstractEmailRecordList());

        Template template = freemarkerConfig.getTemplate("email.ftl");

        StringWriter emailHTML = new StringWriter();
        template.process(freemarkerMap, emailHTML);
        emailHTML.flush();

        for (PubMedAbstractRecord pubMedAbstractRecord : dbMapper.getPubMedAbstractEmailRecordList()){

            PmidEmailRecord pmidEmailRecord = new PmidEmailRecord();
            pmidEmailRecord.setPmid(pubMedAbstractRecord.getPmid());
            pmidEmailRecord.setEmail("alex@woolford.io");

            dbMapper.insertPmidEmailRecord(pmidEmailRecord);
        }

        Email from = new Email(sendgridFrom);
        String subject = "PubMed article feed";
        Email to = new Email("alex@woolford.io");
        Content content = new Content("text/html", emailHTML.toString());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendgridApiKey);
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            System.out.println(response.statusCode);
            System.out.println(response.body);
            System.out.println(response.headers);
        } catch (IOException ex) {
            throw ex;
        }

    }

}
