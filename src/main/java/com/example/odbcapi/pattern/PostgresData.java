package com.example.odbcapi.pattern;

import com.example.odbcapi.model.postgres.customer.Contact;
import com.example.odbcapi.service.ContactServicePostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostgresData implements Source {

    @Autowired
    private ContactServicePostgres contactServicePostgres;

    @Override
    public List<Contact> getAllData() {
        return contactServicePostgres.getAll();
    }
}
