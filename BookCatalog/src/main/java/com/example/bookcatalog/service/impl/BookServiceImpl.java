package com.example.bookcatalog.service.impl;

import com.example.bookcatalog.entity.Author;
import com.example.bookcatalog.entity.Book;
import com.example.bookcatalog.exception.IllegalTitleBookException;
import com.example.bookcatalog.repository.BookRepo;
import com.example.bookcatalog.response.BookResponse;
import com.example.bookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Book addNewBook (String title,
                            Optional<Integer> year,
                            Optional<String> code,
                            Optional<String> annotation,
                            Optional<String> bookPic,
                            Optional<String> bookPath) {
        Book.Builder builder = new Book.Builder();
        builder.withTitle(title);
        builder.withYear(year.orElse(0));
        builder.withCode(code.orElse("none"));
        builder.withAnnotation(annotation.orElse("none"));
        builder.withBookPic(bookPic.orElse("none"));
        builder.withBookPath(bookPath.orElse("none"));
        return builder.build();
    }

    public Book editBookById (Optional<String> title,
                                      Optional<Integer> year,
                                      Optional<String> code,
                                      Optional<String> annotation,
                                      Optional<String> bookPic,
                                      Optional<String> bookPath,
                                      Integer id) {
        Optional<Book> book = findById(id);
        if (book.isPresent()) {
            Book editBook = book.get();
            editBook.setTitle(title.orElse(editBook.getTitle()));
            editBook.setBookPath(bookPath.orElse(editBook.getBookPath()));
            editBook.setBookPic(bookPic.orElse(editBook.getBookPic()));
            editBook.setCode(code.orElse(editBook.getCode()));
            editBook.setAnnotation(annotation.orElse(editBook.getAnnotation()));
            editBook.setYear(year.orElse(editBook.getYear()));

            return addBook(editBook);
        } else {
            Book newBook = addNewBook(title.orElseThrow(IllegalTitleBookException::new),
                    year, code, annotation, bookPic, bookPath);
            newBook.setId(id);

            return addBook(newBook);
        }
    }

    public List<BookResponse> findByTitle (Pageable pageable, String query) {
        Page<Book> bookPage = bookRepo.searchByTitle(pageable, query);

        return getBookResponse(bookPage);
    }

    public List<BookResponse> findByAuthor (Pageable pageable, Author author) {

        Page<Book> bookPage = bookRepo.findAllByAuthors (pageable, author);

        return  getBookResponse(bookPage);
    }

    private List<BookResponse> getBookResponse (Page<Book> bookPage) {
        return bookPage.get().map(BookResponse::new).collect(Collectors.toList());
    }
}
