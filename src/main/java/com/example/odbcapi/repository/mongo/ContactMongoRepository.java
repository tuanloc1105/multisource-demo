package com.example.odbcapi.repository.mongo;

import com.example.odbcapi.model.mongo.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactMongoRepository extends MongoRepository<Contact, UUID> {
}
