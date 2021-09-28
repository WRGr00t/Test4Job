package com.example.bookcatalog.controller;

import com.example.bookcatalog.entity.Book;
import com.example.bookcatalog.exception.BookNotFoundException;
import com.example.bookcatalog.response.BookResponse;
import com.example.bookcatalog.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class ApiBookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping(path = "book", params = {"sort"})
    public List<BookResponse> getBooks(@RequestParam(name = "sort", defaultValue = "year") String sortMode) {
        List<Book> books = bookService.getAll();
        List<BookResponse> responses = books.stream()
                .map(BookResponse::new)
                .sorted(Comparator.comparing(BookResponse::getYear))
                .collect(Collectors.toList());
        return responses;
    }

    @GetMapping("book/{id}")
    public Book getOne(@PathVariable Integer id) {
        return bookService.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PostMapping(path = "book")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PutMapping("book/{id}")
    public Book editBook(@RequestBody Book newBook, @PathVariable Integer id) {

        return bookService.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthors(newBook.getAuthors());
                    book.setBookPath(newBook.getBookPath());
                    book.setBookPic(newBook.getBookPic());
                    book.setCode(newBook.getCode());
                    book.setAnnotation(newBook.getAnnotation());
                    book.setYear(newBook.getYear());
                    return bookService.editBook(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookService.editBook(newBook);
                });
    }

    @DeleteMapping("book/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        bookService.delete(id);
    }

}
