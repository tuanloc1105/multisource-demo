package com.example.odbcapi.pattern;

import com.example.odbcapi.model.mysql.customer.Contact;
import com.example.odbcapi.service.ContactServiceMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MysqlData implements Source {

    @Autowired
    private ContactServiceMysql contactServiceMysql;

    @Override
    public List<Contact> getAllData() {
        return contactServiceMysql.getAll();
    }
}
