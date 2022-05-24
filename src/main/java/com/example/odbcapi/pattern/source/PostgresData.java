package com.example.odbcapi.pattern.source;

import com.example.odbcapi.message.Param;
import com.example.odbcapi.message.response.ProcessResponse;
import com.example.odbcapi.service.ContactServicePostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostgresData implements Source {

    @Autowired
    private ContactServicePostgres contactServicePostgres;

    @Override
    public ProcessResponse getData(Param param) {
        return contactServicePostgres.getDataFromParam(param);
    }

    @Override
    public Object addData(Param param) {
        return contactServicePostgres.addNewContact(param);
    }
}
