package se.yrgo.spring.services.author;

import se.yrgo.spring.domain.*;

import java.util.*;

// Adam

public interface AuthorService {

    public Author addAuthor(String authorId, String name);

    public void deleteAuthor(Author deletedAuthor);

    public Author findAuthorByName(String name);

    public List<Author> getAllAuthors();
}
