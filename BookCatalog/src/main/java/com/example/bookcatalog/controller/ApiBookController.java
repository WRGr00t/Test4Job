package com.example.bookcatalog.controller;

import com.example.bookcatalog.entity.Author;
import com.example.bookcatalog.entity.Book;
import com.example.bookcatalog.exception.AuthorNotFoundException;
import com.example.bookcatalog.exception.BookNotFoundException;
import com.example.bookcatalog.exception.IllegalNameAuthorException;
import com.example.bookcatalog.response.BookResponse;
import com.example.bookcatalog.response.SearchBooksResponse;
import com.example.bookcatalog.service.impl.AuthorServiceImpl;
import com.example.bookcatalog.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        Book newBook = bookService.addNewBook(title, year, code, annotation, bookPic, bookPath);
        Author author = authorService.findByName(authorName);
        if (author == null) {
            author = new Author(authorName);
        }
        authorService.addNewBookToAuthors(newBook, author);

        HashSet<Author> authors = new HashSet<>();
        authors.add(author);
        newBook.setAuthors(authors);
        newBook = bookService.addBook(newBook);
        authorService.addAuthor(author);
        return new BookResponse(newBook);
    }

    @PutMapping("book/{id}")
    public BookResponse editBook(@RequestParam(required = false) Optional<String> title,
                         @RequestParam(required = false) Optional<Integer> year,
                         @RequestParam(required = false) Optional<String> code,
                         @RequestParam(required = false) Optional<String> annotation,
                         @RequestParam(required = false) Optional<String> bookPic,
                         @RequestParam(required = false) Optional<String> bookPath,
                         @RequestParam(required = false) Optional<String> authorName,
                         @PathVariable Integer id) {
        Book book = bookService.editBookById(title, year, code, annotation, bookPic, bookPath, id);
        Author author = authorService.findByName(authorName.orElseThrow(IllegalNameAuthorException::new));
        if (author == null) {
            author = new Author(authorName.get());
            authorService.addAuthor(author);
        }
        Set<Author> authorSet = new HashSet<>();
        authorSet.add(author);
        book.setAuthors(authorSet);
        return new BookResponse(bookService.editBook(book));
    }

    @DeleteMapping("book/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        bookService.delete(id);
    }

    @GetMapping("book/searchbytitle")
    public ResponseEntity<SearchBooksResponse> searchBooksByTitle (@RequestParam(defaultValue = "0") int offset,
                                                                   @RequestParam(defaultValue = "10") int limit,
                                                                   @RequestParam String query) {

        int pageNo = offset / limit;
        Pageable pageable = PageRequest.of(pageNo, limit, Sort.by("title"));
        List<BookResponse> page = bookService.findByTitle(pageable, query);
        SearchBooksResponse searchBooksResponse = new SearchBooksResponse(page.size(), (ArrayList<BookResponse>) page);
        return new ResponseEntity<>(searchBooksResponse, HttpStatus.OK);
    }

    @GetMapping("book/searchbyauthor")
    public ResponseEntity<SearchBooksResponse> searchBooksByAuthorName (@RequestParam(defaultValue = "0") int offset,
                                                                        @RequestParam(defaultValue = "10") int limit,
                                                                        @RequestParam String authorName) {
        int pageNo = offset / limit;
        Pageable pageable = PageRequest.of(pageNo, limit, Sort.by("year"));

        Author author = authorService.findByName(authorName);
        List<BookResponse> page;
        if (author != null) {
            page = bookService.findByAuthor(pageable, author);
        } else {
            throw new AuthorNotFoundException(authorName);
        }
        SearchBooksResponse searchBooksResponse = new SearchBooksResponse(page.size(), (ArrayList<BookResponse>) page);
        return new ResponseEntity<>(searchBooksResponse, HttpStatus.OK);
    }

}
