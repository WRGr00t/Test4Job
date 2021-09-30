package com.example.bookcatalog.controller;

import com.example.bookcatalog.entity.Author;
import com.example.bookcatalog.entity.Book;
import com.example.bookcatalog.exception.BookNotFoundException;
import com.example.bookcatalog.response.BookResponse;
import com.example.bookcatalog.service.impl.AuthorServiceImpl;
import com.example.bookcatalog.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class ApiBookController {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private AuthorServiceImpl authorService;

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
    public BookResponse getOne(@PathVariable Integer id) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return new BookResponse(book);
    }

    @PostMapping(path = "book")
    public BookResponse addBook(@RequestParam String title,
                        @RequestParam(required = false) Optional<Integer> year,
                        @RequestParam(required = false) Optional<String> code,
                        @RequestParam(required = false) Optional<String> annotation,
                        @RequestParam(required = false) Optional<String> bookPic,
                        @RequestParam(required = false) Optional<String> bookPath,
                        @RequestParam String authorName) {
        Book.Builder builder = new Book.Builder();
        builder.withTitle(title);
        builder.withYear(year.orElse(0));
        builder.withCode(code.orElse("none"));
        builder.withAnnotation(annotation.orElse("none"));
        builder.withBookPic(bookPic.orElse("none"));
        builder.withBookPath(bookPath.orElse("none"));
        Book newBook = builder.build();
        System.out.println(newBook.getTitle());
        Author author = authorService.findByName(authorName);
        if (author == null) {
            author = new Author(authorName);
        }
        HashSet<Book> addBook = new HashSet<>();
        addBook.add(newBook);
        author.setBooks(addBook);
        HashSet<Author> authors = new HashSet<>();
        authors.add(author);
        newBook.setAuthors(authors);
        newBook = bookService.addBook(builder.build());
        authorService.addAuthor(authorName);
        return new BookResponse(newBook);
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
