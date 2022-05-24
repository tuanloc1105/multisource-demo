package com.example.odbcapi.pattern.source;

import com.example.odbcapi.message.Param;
import com.example.odbcapi.message.response.ProcessResponse;
import com.example.odbcapi.service.ContactServiceMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MysqlData implements Source {

    @Autowired
    private ContactServiceMysql contactServiceMysql;

    @Override
    public ProcessResponse getData(Param param) {
        return contactServiceMysql.getDataFromParam(param);
    }

    @Override
    public Object addData(Param param) {
        return contactServiceMysql.addNewContact(param);
    }
}
