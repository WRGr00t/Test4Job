package com.example.bookcatalog.repository;

import com.example.bookcatalog.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
}
