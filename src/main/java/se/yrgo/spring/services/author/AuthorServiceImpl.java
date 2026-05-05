package se.yrgo.spring.services.author;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.yrgo.spring.dataaccess.AuthorDao;
import se.yrgo.spring.domain.Author;

// Adam
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao dao;

    public AuthorServiceImpl(AuthorDao dao){
        this.dao = dao;
    }

    @Override
    public void addAuthor(String authorId, String name) {
        Author author = new Author(authorId, name);
        dao.create(author);
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