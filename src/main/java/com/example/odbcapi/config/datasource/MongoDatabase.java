//package com.example.odbcapi.config.datasource;
//
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.bson.UuidRepresentation;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//@Configuration
//@EnableMongoRepositories(
//        basePackages = "com.example.odbcapi.repository.mongo"
//)
//public class MongoDatabase {
//
//    @Value("${spring.datasource.mongodb.authentication-database}")
//    private String mongoAuthDb;
//
//    @Value("${spring.datasource.mongodb.database}")
//    private String mongoDb;
//
//    @Value("${spring.datasource.mongodb.port}")
//    private Integer mongoPort;
//
//    @Value("${spring.datasource.mongodb.host}")
//    private String mongoHost;
//
//    @Value("${spring.datasource.mongodb.uri}")
//    private String mongoUri;
//
//    @Bean
//    public MongoDatabaseFactory mongoDatabaseFactory() {
//        return new SimpleMongoClientDatabaseFactory(this.mongoUri);
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() {
//        return new MongoTemplate(this.mongoDatabaseFactory());
//    }
//
//    @Bean
//    public MongoClient mongoClient() {
//        return MongoClients.create(MongoClientSettings.builder()
//                .uuidRepresentation(UuidRepresentation.STANDARD)
//                .build());
//    }
//
//}
