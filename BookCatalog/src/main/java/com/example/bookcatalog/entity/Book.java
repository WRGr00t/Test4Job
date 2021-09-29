package com.example.bookcatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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

}
