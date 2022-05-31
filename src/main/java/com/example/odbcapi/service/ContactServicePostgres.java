package com.example.odbcapi.service;

import com.example.odbcapi.message.Param;
import com.example.odbcapi.message.response.ProcessResponse;
import com.example.odbcapi.model.postgres.Contact;
import com.example.odbcapi.repository.postgres.ContactPostgresRepository;
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

import static com.example.odbcapi.repository.postgres.ContactPostgresRepository.ContactPostgresSpecifications.hasAge;
import static com.example.odbcapi.repository.postgres.ContactPostgresRepository.ContactPostgresSpecifications.hasName;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(transactionManager = "postgresTransactionManager")
public class ContactServicePostgres {

    private final ApplicationContext context;
    private final ContactPostgresRepository contactPostgresRepository;

    @Autowired
    @Qualifier("postgresEntityManager")
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_POSTGRES)
    private EntityManager entityManager;

    public ProcessResponse addNew(Param param) {
        try {
            String sql = "INSERT INTO public.Contact(name, age) VALUES (:name, :age) RETURNING *";
            Query query = entityManager.createNativeQuery(sql, Contact.class);
            query.setParameter("name", param.getName()).setParameter("age", param.getAge());
            return new ProcessResponse(ProcessStatus.OK, null, (Contact) query.getSingleResult());
        } catch (Exception e) {
            return new ProcessResponse(ProcessStatus.FAILURE, e.getMessage(), null);
        }
    }

    public ProcessResponse addNewContact(Param param) {
        Contact contact = new Contact();
        contact.setName(param.getName());
        contact.setAge(param.getAge());
        return new ProcessResponse(ProcessStatus.OK, null, contactPostgresRepository.save(contact));
    }

    public List<Contact> getAll() {
        String sql = "SELECT * FROM public.contact";
        Query query = entityManager.createNativeQuery(sql, Contact.class);
        return (List<Contact>) query.getResultList();
    }

    public ProcessResponse getDataFromParam(Param param) {
        try {
            if (param != null && (param.getId() != null || param.getName() != null || param.getAge() != null)) {
                String sql = "SELECT * FROM public.contact WHERE %1$s %2$s %3$s";
                String idCondition = "", nameCondition = "", ageCondition = "";
                if (param.getId() != null) {
                    idCondition = "id = " + String.valueOf(param.getId()) + " AND ";
                }
                if (param.getName() != null && !param.getName().isEmpty()) {
                    nameCondition = "name = '" + String.valueOf(param.getName()) + "' AND ";
                }
                if (param.getAge() != null) {
                    ageCondition = "age = " + String.valueOf(param.getAge()) + " AND ";
                }
                String processSql = String.format(sql, idCondition, nameCondition, ageCondition).strip();
                String finalSql = processSql.substring(0, processSql.length() - 3);
                Query query = entityManager.createNativeQuery(finalSql, Contact.class);
                return new ProcessResponse(ProcessStatus.OK, null, (List<Contact>) query.getResultList());
            }
            return new ProcessResponse(ProcessStatus.OK, null, contactPostgresRepository.findAll());
        } catch (Exception e) {
            return new ProcessResponse(ProcessStatus.FAILURE, e.getMessage(), null);
        }

    }

    public ProcessResponse getContactByNameAndAge(String name, int age) {
        return new ProcessResponse(ProcessStatus.OK,
                null,
                this.contactPostgresRepository.findAll(where(hasName(name)).and(hasAge(age))));
    }

}
