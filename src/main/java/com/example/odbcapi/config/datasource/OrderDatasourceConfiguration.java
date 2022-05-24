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
//@Component("order")
//@Slf4j
//@NoArgsConstructor
//public class OrderDatasourceConfiguration {
//
//    @Value("${spring.datasource.order.url}")
//    private String orderUrl;
//
//    @Value("${spring.datasource.order.username}")
//    private String orderUsername;
//
//    @Value("${spring.datasource.order.password}")
//    private String orderPassword;
//
//    @Bean("orderDatasource")
//    @Qualifier("orderDatasources")
//    public Connection orderDataSource() throws SQLException {
//        log.info("GENERATE ORDER DATA SOURCE CONNECTION");
//        return DriverManager.getConnection(this.orderUrl, this.orderUsername, this.orderPassword);
//    }
//}
