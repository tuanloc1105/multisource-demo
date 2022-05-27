package com.example.odbcapi.config.datasource;

import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDatabase {

    @Value("${spring.datasource.mongodb.authentication-database}")
    private String mongoAuthDb;
    
    @Value("${spring.datasource.mongodb.database}")
    private String mongoDb;
    
    @Value("${spring.datasource.mongodb.port}")
    private Integer mongoPort;
    
    @Value("${spring.datasource.mongodb.host}")
    private String mongoHost;
    

    public MongoProperties getPrimary() {
        MongoProperties mongoProperties = new MongoProperties();
        mongoProperties.setUuidRepresentation(UuidRepresentation.JAVA_LEGACY);
        mongoProperties.setAuthenticationDatabase(this.mongoAuthDb);
        mongoProperties.setDatabase(this.mongoDb);
        mongoProperties.setHost(this.mongoHost);
        mongoProperties.setPort(this.mongoPort);
        return new MongoProperties();
    }

}
