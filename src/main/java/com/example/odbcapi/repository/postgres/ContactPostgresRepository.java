package com.example.odbcapi.repository.postgres;

import com.example.odbcapi.model.postgres.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactPostgresRepository extends JpaRepository<Contact, Integer> {
}
