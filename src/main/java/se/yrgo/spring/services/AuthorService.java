package se.yrgo.spring.services;

import se.yrgo.spring.domain.Author;

import java.util.List;

public interface AuthorService {

    public void addAuthor(Author newAuthor);

    public void updateAuthor(Author updatedAuthor);

    public void deleteAuthor(Author deletedAuthor);

    public Author findAuthorById(String authorId);

    public List<Author> getAllAuthors();
}
