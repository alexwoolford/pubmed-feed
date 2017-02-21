package io.woolford;

import io.woolford.database.entity.PubMedAbstractRestRecord;
import io.woolford.database.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    DbMapper dbMapper;

    @CrossOrigin
    @RequestMapping(value = "/pubmed_abstracts")
    @ResponseBody
    public List<PubMedAbstractRestRecord> getPubMedAbstractRestRecordList() {
        return dbMapper.getPubMedAbstractRestRecordList();
    }

}
