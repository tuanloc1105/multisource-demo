//package com.example.odbcapi.config.datasource;
//
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//@Component("customer")
//@Slf4j
//@NoArgsConstructor
//public class CustomerDatasourceConfiguration {
//
//    @Value("${spring.datasource.customer.url}")
//    private String customerUrl;
//
//    @Value("${spring.datasource.customer.username}")
//    private String customerUsername;
//
//    @Value("${spring.datasource.customer.password}")
//    private String customerPassword;
//
//    @Bean("customerDatasource")
//    @Qualifier("customerDatasources")
//    public Connection customerDataSource() throws SQLException {
//        log.info("GENERATE CUSTOMER DATA SOURCE CONNECTION");
//        return DriverManager.getConnection(this.customerUrl, this.customerUsername, this.customerPassword);
//    }
//
//}
