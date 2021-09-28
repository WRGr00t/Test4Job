package com.example.bookcatalog.response;

import com.example.bookcatalog.entity.Author;
import com.example.bookcatalog.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResponse {
    private String title;
    private String author;
    private int year;

    public BookResponse(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthors().stream()
                .map(Author::getName)
                .collect(Collectors.joining(", "));
        this.year = book.getYear();
    }
}
