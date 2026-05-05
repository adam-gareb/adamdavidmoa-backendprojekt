package se.yrgo.spring.services.book;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import se.yrgo.spring.dataaccess.BookDao;
import se.yrgo.spring.dataaccess.BookNotFoundException;
import se.yrgo.spring.domain.Author;
import se.yrgo.spring.domain.Book;

// Moa

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private BookDao dao;

    public BookServiceImpl(BookDao dao) {
        this.dao = dao;
    }

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
    public void registerNewBook(String isbn, String title, Set<Author> authors) {

        try {
            Book newBook = new Book(isbn, title, authors);
            dao.registerNewBook(newBook);
        } catch (Exception ex) {
            System.err.println("Something went wrong when registering book: " + ex.getMessage());
        }

    }

    @Override
    public void deleteFromStock(String isbn) {

        try {
            Book book = dao.findByIsbn(isbn);
            dao.delete(book);
        } catch (BookNotFoundException ex) {
            System.err.println("Something went wrong when searching for book: " + ex.getMessage());
        }
    }

}