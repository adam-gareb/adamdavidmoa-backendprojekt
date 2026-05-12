package se.yrgo.spring.dataaccess;

import java.util.*;

import se.yrgo.spring.domain.Author;

// Adam

public interface AuthorDao {
    public void create(Author author);
    public void delete(Author author);
    public Author findByName(String name);
    public List<Author> getAllAuthors();
}