package com.example.odbcapi.service;

import com.example.odbcapi.model.postgres.customer.Contact;
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
@Transactional(transactionManager = "postgresTransactionManager")
public class ContactServicePostgres {

    private final ApplicationContext context;

    @Autowired
    @Qualifier("postgresEntityManager")
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_POSTGRES)
    private EntityManager entityManager;

    public Contact addNewContact(Contact contact) {
        String sql = "INSERT INTO public.Contact(name, age) VALUES (:name, :age) RETURNING *";
        Query query = entityManager.createNativeQuery(sql, Contact.class);
        query.setParameter("name", contact.getName()).setParameter("age", contact.getAge());
        return (Contact) query.getSingleResult();
    }

    public List<Contact> getAll() {
        String sql = "SELECT * FROM public.contact";
        Query query = entityManager.createNativeQuery(sql, Contact.class);
        return (List<Contact>) query.getResultList();
    }

}
