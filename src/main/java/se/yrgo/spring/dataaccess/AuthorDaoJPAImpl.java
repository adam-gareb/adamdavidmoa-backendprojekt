package se.yrgo.spring.dataaccess;

import java.util.*;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import se.yrgo.spring.domain.Author;
import se.yrgo.spring.domain.Book;

// Adam

@Repository
public class AuthorDaoJPAImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Author author) {
        em.persist(author);
    }

    @Override
    public void delete(Author author) {
        Author theAuthor = em.find(Author.class, author.getId());
        if (theAuthor != null) {
            // Ta bort författaren från alla böckers ägande sida
            for (Book book : theAuthor.getAllBooks()) {
                book.getAuthors().remove(theAuthor);
            }
            em.remove(theAuthor);
        }
    }

    @Override
    public Author findByName(String name) {
        return em.createQuery("SELECT a FROM Author a WHERE a.name = :name", Author.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Author> getAllAuthors() {
        return em.createQuery("SELECT a FROM Author a ", Author.class)
                .getResultList();
    }
}