package com.example.bookcatalog.service;

import com.example.bookcatalog.entity.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {
    Book addBook(Book book);

    Optional<Book> findById(int id);

    Book findByTitle(String title);

    List<Book> getAll();

    Book editBook(Book book);

    void delete(int id);
}
