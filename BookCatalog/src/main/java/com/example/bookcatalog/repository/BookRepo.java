package com.example.bookcatalog.repository;

import com.example.bookcatalog.entity.Author;
import com.example.bookcatalog.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepo extends CrudRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book findByTitle(@Param("title") String title);

    Optional<Book> findById(Integer id);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:query%")
    Page<Book> searchByTitle(Pageable pageable, @Param("query") String query);

    Page<Book> findAllByAuthors(Pageable pageable, @Param("name") Author author);

}
