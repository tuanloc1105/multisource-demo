package com.example.odbcapi.repository.postgres;

import com.example.odbcapi.model.postgres.Contact;
import com.example.odbcapi.repository.BaseRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContactPostgresRepository extends BaseRepository<Contact>, JpaSpecificationExecutor<Contact> {

    class ContactPostgresSpecifications {
        public static Specification<Contact> hasName(String name) {
            return (contact, cq, cb) -> cb.like(contact.get("name"), "%" + name + "%");
        }

        public static Specification<Contact> hasAge(int age) {
            return (contact, cq, cb) -> cb.equal(contact.get("age"), age);
        }
    }
}
