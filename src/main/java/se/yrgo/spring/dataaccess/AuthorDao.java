package se.yrgo.spring.dataaccess;

import java.util.List;

import se.yrgo.spring.domain.Author;

public interface AuthorDao {
    public void create();
    public void delete();
    public Author findByName(String name);
    public List<Author> getAllAuthors();
}