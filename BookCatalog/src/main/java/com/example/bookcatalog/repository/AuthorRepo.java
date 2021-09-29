package com.example.bookcatalog.repository;

import com.example.bookcatalog.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorRepo extends CrudRepository<Author, Integer> {

    @Query("select a from Author a where a.name = :name")
    Author findByName(@Param("name") String name);
}
