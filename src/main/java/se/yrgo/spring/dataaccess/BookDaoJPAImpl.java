package se.yrgo.spring.dataaccess;

import java.util.*;

import org.springframework.stereotype.Repository;

import jakarta.persistence.*;
import se.yrgo.spring.domain.Book;

// Moa

@Repository
public class BookDaoJPAImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> getEntireCatalogue() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public Book findByIsbn(String isbn) throws BookNotFoundException {
        return em.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }

    @Override
    public void registerNewBook(Book newBook) {
        em.persist(newBook);
    }

    @Override
    public void delete(Book redundantBook) {
        Book managed = em.merge(redundantBook);
        em.remove(managed);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return em.createQuery("SELECT b FROM Book b JOIN b.authors a WHERE a.name = :author", Book.class)
                .setParameter("author", author)
                .getResultList();
    }

    @Override
    public void updateBook(Book book) {
        em.merge(book);
    }
}
