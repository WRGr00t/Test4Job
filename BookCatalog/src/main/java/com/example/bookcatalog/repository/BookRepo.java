package com.example.bookcatalog.repository;

import com.example.bookcatalog.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepo extends CrudRepository<Book, Integer> {

    @Query("select b from Book b where b.title = :title")
    Book findByTitle(@Param("title") String title);

    Optional<Book> findById(Integer id);
}
