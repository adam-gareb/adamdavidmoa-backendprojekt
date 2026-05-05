package se.yrgo.spring.services.author;

import se.yrgo.spring.domain.Author;

import java.util.List;

// Adam
public interface AuthorService {

    public void addAuthor(String authorId, String name);

    public void deleteAuthor(Author deletedAuthor);

    public Author findAuthorByName(String name);

    public List<Author> getAllAuthors();
}
