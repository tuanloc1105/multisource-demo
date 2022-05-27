package com.example.odbcapi.service;

import com.example.odbcapi.message.Param;
import com.example.odbcapi.message.response.ProcessResponse;
import com.example.odbcapi.model.mongo.Contact;
import com.example.odbcapi.repository.mongo.ContactMongoRepository;
import com.example.odbcapi.value.ProcessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceMongo {

    private final ContactMongoRepository contactMongoRepository;

    public ProcessResponse addNew(Param param) {
        Contact contact = new Contact();
        contact.setName(param.getName());
        contact.setAge(param.getAge());
        return new ProcessResponse(ProcessStatus.OK, null, contactMongoRepository.save(contact));
    }

    public ProcessResponse getDataFromParam(Param param) {
        return new ProcessResponse(ProcessStatus.OK, null, contactMongoRepository.findAll());
    }

}
