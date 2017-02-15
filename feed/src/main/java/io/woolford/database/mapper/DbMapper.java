package io.woolford.database.mapper;

import io.woolford.database.entity.DoctorRecord;
import io.woolford.database.entity.PubMedAbstractRecord;
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
    void insertPubMedAbstractRecord(PubMedAbstractRecord pubMedAbstractRecord);

}