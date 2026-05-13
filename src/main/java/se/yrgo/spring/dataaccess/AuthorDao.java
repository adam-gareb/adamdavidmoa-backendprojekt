package se.yrgo.spring.dataaccess;

import java.util.*;

import se.yrgo.spring.domain.Author;

// Adam
// A DAO interface for Author, for the service class AuthorService
public interface AuthorDao {
    public void create(Author author);
    public void delete(Author author);
    public Author findByName(String name) throws AuthorNotFoundException;
    public List<Author> getAllAuthors();
}