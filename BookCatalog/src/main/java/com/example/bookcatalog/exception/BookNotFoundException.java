package com.example.bookcatalog.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Integer id) {
        super("Could not find book " + id);
    }
}
