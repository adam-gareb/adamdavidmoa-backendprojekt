package se.yrgo.spring.services.author;

import se.yrgo.spring.dataaccess.AuthorNotFoundException;
import se.yrgo.spring.domain.*;

import java.util.*;

// Adam
// Service interface for Author
public interface AuthorService {

    public Author addAuthor(String authorId, String name);

    public void deleteAuthor(Author deletedAuthor);

    public Author findAuthorByName(String name) throws AuthorNotFoundException;

    public List<Author> getAllAuthors();
}
