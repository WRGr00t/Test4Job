package com.example.bookcatalog.service.impl;

import com.example.bookcatalog.entity.Book;
import com.example.bookcatalog.repository.BookRepo;
import com.example.bookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookRepo.findById(id);
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepo.findByTitle(title);
    }

    @Override
    public List<Book> getAll() {
        Iterable<Book> iterable = bookRepo.findAll();
        List<Book> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    @Override
    public Book editBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public void delete(int id) {
        bookRepo.deleteById(id);
    }
}
