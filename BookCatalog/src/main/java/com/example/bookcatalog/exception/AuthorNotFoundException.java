package com.example.bookcatalog.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Integer id) {
        super("Could not find author " + id);
    }
}