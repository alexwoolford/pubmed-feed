package io.woolford.database.mapper;

import io.woolford.database.entity.DoctorRecord;
import io.woolford.database.entity.PmidEmailRecord;
import io.woolford.database.entity.PubMedAbstractRecord;
import io.woolford.database.entity.PubMedAbstractRestRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DbMapper {

    @Select("SELECT doctorName FROM pubmed_feed.doctors")
    List<DoctorRecord> getDoctorsRecordList();

    @Insert("INSERT INTO pubmed_feed.pubmed_abstracts (" +
            "   pmid,                                  " +
            "   title,                                 " +
            "   abstractText,                          " +
            "   journal,                               " +
            "   createDate,                            " +
            "   lastname,                              " +
            "   forename,                              " +
            "   initials)                              " +
            "VALUES                                    " +
            "    (#{pmid},                             " +
            "     #{title},                            " +
            "     #{abstractText},                     " +
            "     #{journal},                          " +
            "     #{createDate},                       " +
            "     #{lastname},                         " +
            "     #{forename},                         " +
            "     #{initials})                         " +
            "ON DUPLICATE KEY UPDATE                   " +
            "     title=#{title},                      " +
            "     abstractText=#{abstractText},        " +
            "     journal=#{journal},                  " +
            "     createDate=#{createDate},            " +
            "     lastname=#{lastname},                " +
            "     forename=#{forename},                " +
            "     initials=#{initials}                 ")
    void insertPubMedAbstractRecord(PubMedAbstractRecord pubMedAbstractRecord);

    @Select("SELECT           " +
            "   pmid,         " +
            "   title,        " +
            "   abstractText, " +
            "   journal,      " +
            "   createDate,   " +
            "   lastname,     " +
            "   forename,     " +
            "   initials      " +
            "FROM pubmed_feed.pubmed_abstracts")
    List<PubMedAbstractRestRecord> getPubMedAbstractRestRecordList();

    @Insert("INSERT INTO pubmed_feed.email_sent (" +
            "   pmid,                            " +
            "   email)                           " +
            "VALUES                              " +
            "    (#{pmid},                       " +
            "     #{email})                      ")
    void insertPmidEmailRecord(PmidEmailRecord pmidEmailRecord);

    @Select("SELECT                                     " +
            "   pubmed_abstracts.pmid,                  " +
            "   title,                                  " +
            "   abstractText,                           " +
            "   journal,                                " +
            "   createDate,                             " +
            "   lastname,                               " +
            "   forename,                               " +
            "   initials                                " +
            "FROM pubmed_feed.pubmed_abstracts          " +
            "LEFT OUTER JOIN pubmed_feed.email_sent     " +
            "ON pubmed_abstracts.pmid = email_sent.pmid " +
            "WHERE email_sent.pmid IS NULL              ")
    List<PubMedAbstractRecord> getPubMedAbstractEmailRecordList();

}