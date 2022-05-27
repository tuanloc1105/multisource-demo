package com.example.odbcapi.pattern.source;

import com.example.odbcapi.message.Param;
import com.example.odbcapi.message.response.ProcessResponse;
import com.example.odbcapi.service.ContactServiceMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoData implements Source {

    @Autowired
    private ContactServiceMongo contactServiceMongo;

    @Override
    public ProcessResponse getData(Param param) {
        return contactServiceMongo.getDataFromParam(param);
    }

    @Override
    public ProcessResponse addData(Param param) {
        return contactServiceMongo.addNew(param);
    }
}
