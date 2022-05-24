package com.example.odbcapi.service;

import com.example.odbcapi.model.mysql.customer.Contact;
import com.example.odbcapi.value.Constants;
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
@Transactional(transactionManager = "mysqlTransactionManager")
public class ContactServiceMysql {

    private final ApplicationContext context;

    @Autowired
    @Qualifier("mysqlEntityManager")
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_MYSQL)
    private EntityManager entityManager;

    public Contact addNewContact(Contact contact) {
        String sql = "INSERT INTO `contact`(name, age) VALUES (:name, :age)";
        Query query = entityManager.createNativeQuery(sql, Contact.class).setParameter("name", contact.getName()).setParameter("age", contact.getAge());
        query.executeUpdate();
        Query query1 = entityManager.createNativeQuery("SELECT * FROM `contact` ORDER BY id DESC LIMIT 1", Contact.class);
        return (Contact) query1.getSingleResult();
    }

    public List<Contact> getAll() {
        String sql = "SELECT * FROM `contact`";
        Query query = entityManager.createNativeQuery(sql, Contact.class);
        return (List<Contact>) query.getResultList();
    }

}
