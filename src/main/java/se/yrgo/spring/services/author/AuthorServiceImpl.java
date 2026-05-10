package se.yrgo.spring.services.author;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.spring.dataaccess.*;
import se.yrgo.spring.domain.*;

// Adam
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao dao;

    public AuthorServiceImpl(AuthorDao dao) {
        this.dao = dao;
    }

    @Override
    public Author addAuthor(String authorId, String name) {
        Author author = new Author(authorId, name);
        dao.create(author);
        return author;
    }

    @Override
    public void deleteAuthor(Author deletedAuthor) {
        dao.delete(deletedAuthor);
    }

    @Override
    public Author findAuthorByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Author> getAllAuthors() {
        return dao.getAllAuthors();
    }

}