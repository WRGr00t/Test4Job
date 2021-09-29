package com.example.bookcatalog.service;

import com.example.bookcatalog.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author addAuthor(String name);

    Optional<Author> findById(int id);

    Author findByName(String name);

    List<Author> getAll();

    Author editAuthor(Author author);

    void delete(int id);
}
