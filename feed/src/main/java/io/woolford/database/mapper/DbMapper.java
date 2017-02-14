package io.woolford.database.mapper;

import io.woolford.database.entity.PubMedAbstractRecord;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

@Component
public interface DbMapper {

    @Insert("INSERT INTO pubmed_feed.pubmed_abstracts (" +
            "   pmid,                                  " +
            "   title,                                 " +
            "   journalTitle,                          " +
            "   createDate,                            " +
            "   docAbstract)                           " +
            "VALUES                                    " +
            "    (#{pmid},                             " +
            "     #{title},                            " +
            "     #{journalTitle},                     " +
            "     #{createDate},                       " +
            "     #{docAbstract})                      " +
            "ON DUPLICATE KEY UPDATE                   " +
            "     title=#{title},                      " +
            "     journalTitle=#{journalTitle},        " +
            "     createDate=#{createDate},            " +
            "     docAbstract=#{docAbstract}")
    public void insertPubMedAbstractRecord(PubMedAbstractRecord pubMedAbstractRecord);

}