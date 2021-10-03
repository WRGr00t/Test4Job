package com.example.bookcatalog.exception;

public class IllegalNameAuthorException extends RuntimeException{
    public IllegalNameAuthorException() {
        super("Author's name not be empty");
    }
}
