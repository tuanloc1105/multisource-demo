package com.example.odbcapi.service;

import com.example.odbcapi.constant.Constants;
import com.example.odbcapi.model.customer.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(transactionManager = "customerTransactionManager")
public class CustomerService {

    private final ApplicationContext context;

    @Autowired
    @Qualifier("customerEntityManager")
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_CUSTOMER)
    private EntityManager entityManager;

    public Customer addNewCustomer(Customer customer) {
        String sql = "INSERT INTO public.customer(name, age) VALUES (:name, :age) RETURNING *";
        Query query = entityManager.createNativeQuery(sql, Customer.class);
        query.setParameter("name", customer.getName()).setParameter("age", customer.getAge());
        return (Customer) query.getSingleResult();
    }

    public List<Customer> getAll() {
        String sql = "SELECT * FROM public.customer";
        Query query = entityManager.createNativeQuery(sql, Customer.class);
        return (List<Customer>) query.getResultList();
    }

}
