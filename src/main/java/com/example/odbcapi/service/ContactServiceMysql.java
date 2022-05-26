package com.example.odbcapi.service;

import com.example.odbcapi.message.Param;
import com.example.odbcapi.message.response.ProcessResponse;
import com.example.odbcapi.model.mysql.Contact;
import com.example.odbcapi.repository.mysql.ContactMysqlRepository;
import com.example.odbcapi.value.Constants;
import com.example.odbcapi.value.ProcessStatus;
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
    private final ContactMysqlRepository contactMysqlRepository;

    @Autowired
    @Qualifier("mysqlEntityManager")
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_MYSQL)
    private EntityManager entityManager;

    public ProcessResponse addNewContact(Param param) {
        try {
            String sql = "INSERT INTO `contact`(name, age) VALUES (:name, :age)";
            Query query = entityManager.createNativeQuery(sql, Contact.class).setParameter("name", param.getName()).setParameter("age", param.getAge());
            query.executeUpdate();
            Query query1 = entityManager.createNativeQuery("SELECT * FROM `contact` ORDER BY id DESC LIMIT 1", Contact.class);
            return new ProcessResponse(ProcessStatus.OK, null, (Contact) query1.getSingleResult());
        } catch (Exception e) {
            return new ProcessResponse(ProcessStatus.FAILURE, e.getMessage(), null);
        }
    }

    public List<Contact> getAll() {
        String sql = "SELECT * FROM `contact`";
        Query query = entityManager.createNativeQuery(sql, Contact.class);
        return (List<Contact>) query.getResultList();
    }

    public ProcessResponse getDataFromParam(Param param) {
        try {
            if (param != null && (param.getId() != null || param.getName() != null || param.getAge() != null)) {
                String sql = "SELECT * FROM `contact` WHERE %1$s %2$s %3$s";
                String idCondition = "", nameCondition = "", ageCondition = "";
                if (param.getId() != null) {
                    idCondition = " id = " + String.valueOf(param.getId()) + " AND";
                }
                if (param.getName() != null && !param.getName().isEmpty()) {
                    nameCondition = "name = '" + String.valueOf(param.getName()) + "' AND ";
                }
                if (param.getAge() != null) {
                    ageCondition = " age = " + String.valueOf(param.getAge()) + " AND";
                }
                String processSql = String.format(sql, idCondition, nameCondition, ageCondition).strip();
                String finalSql = processSql.substring(0, processSql.length() - 3);
                Query query = entityManager.createNativeQuery(finalSql, Contact.class);
                return new ProcessResponse(ProcessStatus.OK, null, (List<Contact>) query.getResultList());
            }
            contactMysqlRepository.findAll();
            String sql = "SELECT * FROM `contact`";
            Query query = entityManager.createNativeQuery(sql, Contact.class);
            return new ProcessResponse(ProcessStatus.OK, null, (List<Contact>) query.getResultList());
        } catch (Exception e) {
            return new ProcessResponse(ProcessStatus.FAILURE, e.getMessage(), null);
        }

    }

}
