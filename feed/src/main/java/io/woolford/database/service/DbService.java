package io.woolford.database.service;


import io.woolford.database.entity.DoctorRecord;
import io.woolford.database.entity.PubMedAbstractRecord;
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

}