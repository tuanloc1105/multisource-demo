package com.example.odbcapi.config.mongo;

import com.example.odbcapi.model.mongo.Contact;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

@Configuration
public class GenerateBasicFieldValue extends AbstractMongoEventListener<Contact> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Contact> event) {
        Contact entity = event.getSource();
        if (entity.isNew()) {
            entity.setId(UUID.randomUUID());
        }
    }
}