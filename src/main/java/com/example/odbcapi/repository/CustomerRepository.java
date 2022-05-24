package com.example.odbcapi.repository;

import com.example.odbcapi.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerRepository {

    @Autowired
    @Qualifier("customerEntityManager")
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_CUSTOMER)
    private EntityManager entityManager;
}
