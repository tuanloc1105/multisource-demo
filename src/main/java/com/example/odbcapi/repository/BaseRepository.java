package com.example.odbcapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<E> extends JpaRepository<E, Integer> {
}
