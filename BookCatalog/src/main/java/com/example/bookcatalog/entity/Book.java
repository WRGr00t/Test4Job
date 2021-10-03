package com.example.bookcatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "books")
    private Set<Author> authors;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    String code;

    @Column(nullable = false)
    String annotation;

    @Column(nullable = false)
    String bookPic;

    @Column(nullable = false)
    String bookPath;

    public static class Builder {
        private Book newBook;

        public Builder() {
            newBook = new Book();
        }

        public Builder withTitle(String title){
            newBook.setTitle(title);
            return this;
        }

        public Builder withYear(int year){
            newBook.setYear(year);
            return this;
        }

        public Builder withCode(String code){
            newBook.setCode(code);
            return this;
        }

        public Builder withAnnotation (String annotation){
            newBook.setAnnotation(annotation);
            return this;
        }

        public Builder withBookPic(String bookPic){
            newBook.setBookPic(bookPic);
            return this;
        }

        public Builder withBookPath(String bookPath){
            newBook.setBookPath(bookPath);
            return this;
        }

        public Book build(){
            return newBook;
        }
    }

}
