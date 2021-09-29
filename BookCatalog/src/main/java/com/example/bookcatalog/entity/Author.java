package com.example.bookcatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name="author_book",
            joinColumns = @JoinColumn(name="author_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="book_id", referencedColumnName="id"))
    private Set<Book> books;

    public Author(String name) {
        this.name = name;
        this.books = new HashSet<>();
    }
}
