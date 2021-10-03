package com.example.bookcatalog.exception;

public class IllegalTitleBookException extends RuntimeException{
    public IllegalTitleBookException() {
        super("Title not be empty");
    }
}
