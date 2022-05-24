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
public class CustomerDatabase {


    @Value("${spring.datasource.customer.url}")
    private String customerUrl;

    @Value("${spring.datasource.customer.username}")
    private String customerUsername;

    @Value("${spring.datasource.customer.password}")
    private String customerPassword;

    @Value("${spring.datasource.customer.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource customerDatasources() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(customerUrl);
        dataSource.setUsername(customerUsername);
        dataSource.setPassword(customerPassword);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean customerEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(customerDatasources());

        // Scan Entities in Package:
        em.setPackagesToScan(new String[]{Constants.PACKAGE_ENTITIES_CUSTOMER});
        em.setPersistenceUnitName(Constants.JPA_UNIT_NAME_CUSTOMER); // Important !!

        //
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();

        // JPA & Hibernate
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put("hibernate.show-sql", true);
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", false);

        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean
    public PlatformTransactionManager customerTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(customerEntityManager().getObject());
        return transactionManager;
    }
}
