package se.yrgo.spring.services.book;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.yrgo.spring.dataaccess.BookDao;
import se.yrgo.spring.dataaccess.BookNotFoundException;
import se.yrgo.spring.domain.Book;

// Moa

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @PersistenceContext
    private EntityManager em;

    private BookDao dao;

    @Override
    public List<Book> getAllBooksByAuthor(String author) {
        return dao.findBooksByAuthor(author);
    }

    @Override
    public Book getBookByIsbn(String isbn) throws BookNotFoundException {
        return dao.findByIsbn(isbn);
    }

    @Override
    public List<Book> getEntireCatalogue() {
        return dao.getEntireCatalogue();
    }

    @Override
    public void registerNewBook(Book newBook) {
        dao.registerNewBook(newBook);
    }

    @Override
    public void deleteFromStock(Book book) {
        dao.delete(book);
    }

}