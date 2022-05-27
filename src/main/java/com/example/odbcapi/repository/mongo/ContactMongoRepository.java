package com.example.odbcapi.repository.mongo;

import com.example.odbcapi.model.mongo.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ContactMongoRepository extends MongoRepository<Contact, UUID> {
}
