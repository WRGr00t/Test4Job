package com.example.bookcatalog.service.impl;

import com.example.bookcatalog.entity.Author;
import com.example.bookcatalog.entity.Book;
import com.example.bookcatalog.exception.AuthorNotFoundException;
import com.example.bookcatalog.repository.AuthorRepo;
import com.example.bookcatalog.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public Author addAuthor(String name) {
        Author newAuthor = findByName(name);
        if (newAuthor == null) {
            newAuthor = new Author(name);
        }
        return authorRepo.save(newAuthor);
    }

    @Override
    public Optional<Author> findById(int id) {
        return authorRepo.findById(id);
    }

    @Override
    public Author findByName(String name) {
        return authorRepo.findByName(name);
    }

    @Override
    public List<Author> getAll() {
        List<Author> authors = null;
        for (Author author : authorRepo.findAll()) {
            authors.add(author);
        }
        return authors;
    }

    @Override
    public Author editAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public void delete(int id) {
        authorRepo.delete(authorRepo.findById(id).orElseThrow(() -> new AuthorNotFoundException(id)));
    }

    public Set<Author> setAuthorsFromString(String authors) {
        HashSet<Author> authorSet = new HashSet<>();
        String[] authorsArray = authors.split(", ");
        for (String name : authorsArray) {
            Author author = findByName(name);
            if (author == null) {
                author = addAuthor(name);
            }
            authorSet.add(author);
        }
        return authorSet;
    }

    public void addNewBookToAuthors (Book newBook, Author author) {
        Set<Book> newBookForAuthor = author.getBooks();
        newBookForAuthor.add(newBook);
        author.setBooks(newBookForAuthor);
    }

    public Author addAuthor (Author author) {
        return authorRepo.save(author);
    }
}
