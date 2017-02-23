package io.woolford.database.service;


import io.woolford.database.entity.DoctorRecord;
import io.woolford.database.entity.PmidEmailRecord;
import io.woolford.database.entity.PubMedAbstractRecord;
import io.woolford.database.entity.PubMedAbstractRestRecord;
import io.woolford.database.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    @Autowired
    private DbMapper dbMapper;

    public void insertPubMedAbstractRecord(PubMedAbstractRecord pubMedAbstractRecord){
        dbMapper.insertPubMedAbstractRecord(pubMedAbstractRecord);
    }

    public List<DoctorRecord> getDoctorsRecordList(){
        return dbMapper.getDoctorsRecordList();
    }

    public List<PubMedAbstractRestRecord> getPubMedAbstractRestRecordList() {
        return dbMapper.getPubMedAbstractRestRecordList();
    }

    public void insertPmidEmailRecord(PmidEmailRecord pmidEmailRecord){
        dbMapper.insertPmidEmailRecord(pmidEmailRecord);
    }

    public List<PubMedAbstractRecord> getPubMedAbstractEmailRecordList() {
        return dbMapper.getPubMedAbstractEmailRecordList();
    }

}