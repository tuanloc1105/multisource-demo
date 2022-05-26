package com.example.odbcapi.repository.mysql;

import com.example.odbcapi.model.mysql.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMysqlRepository extends JpaRepository<Contact, Integer> {
}
