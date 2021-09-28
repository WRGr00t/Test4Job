package com.example.bookcatalog.repository;

import com.example.bookcatalog.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepo extends CrudRepository<Author, Integer> {
}
