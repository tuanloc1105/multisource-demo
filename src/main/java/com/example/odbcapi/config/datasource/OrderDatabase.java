package com.example.odbcapi.config.datasource;

import com.example.odbcapi.constant.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class OrderDatabase {
    @Value("${spring.datasource.order.url}")
    private String orderUrl;

    @Value("${spring.datasource.order.username}")
    private String orderUsername;

    @Value("${spring.datasource.order.password}")
    private String orderPassword;

    @Value("${spring.datasource.order.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource orderDatasources() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(orderUrl);
        dataSource.setUsername(orderUsername);
        dataSource.setPassword(orderPassword);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean orderEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(orderDatasources());

        // Scan Entities in Package:
        em.setPackagesToScan(new String[]{Constants.PACKAGE_ENTITIES_2});
        em.setPersistenceUnitName(Constants.JPA_UNIT_NAME_ORDER); // Important !!

        //
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();

        // JPA & Hibernate
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show-sql", true);
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", false);

        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean
    public PlatformTransactionManager orderTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(orderEntityManager().getObject());
        return transactionManager;
    }
}
