package com.example.bookcatalog.controller;

import com.example.bookcatalog.exception.AuthorNotFoundException;
import com.example.bookcatalog.exception.BookNotFoundException;
import com.example.bookcatalog.exception.IllegalNameAuthorException;
import com.example.bookcatalog.exception.IllegalTitleBookException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BookNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(BookNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String authorNotFoundHandler(AuthorNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IllegalTitleBookException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String IllegalTitleArgumentHandler(IllegalTitleBookException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IllegalNameAuthorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String IllegalNameArgumentHandler(IllegalNameAuthorException ex) {
        return ex.getMessage();
    }
}
