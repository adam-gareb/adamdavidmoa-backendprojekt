package se.yrgo.spring.dataaccess;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import se.yrgo.spring.domain.Book;

// Skrivit ihop
public class BookDaoJPAImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> getEntireCatalogue() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public Book findByIsbn(String isbn) throws BookNotFoundException {
        return em.createQuery("FROM Book b WHERE isbn = :isbn", Book.class).setParameter("isbn", isbn)
                .getSingleResult();
    }

    @Override
    public void registerNewBook(Book newBook) {
        em.persist(newBook);
    }

    @Override
    public void delete(Book redundantBook) {
        em.remove(redundantBook);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return em.createQuery("FROM BOOK b WHERE b.author = :author", Book.class).setParameter("author", author)
                .getResultList();
    }

}
