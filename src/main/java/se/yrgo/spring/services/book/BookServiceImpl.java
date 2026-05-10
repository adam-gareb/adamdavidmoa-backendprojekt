package se.yrgo.spring.services.book;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.spring.dataaccess.*;
import se.yrgo.spring.domain.*;

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
    public Book registerNewBook(String isbn, String title, Set<Author> authors) {
        
        Book newBook = new Book();
        try {
            newBook.setIsbn(isbn);
            newBook.setTitle(title);
            newBook.setAuthors(authors);

            dao.registerNewBook(newBook);

        } catch (Exception ex) {
            System.err.println("Something went wrong when registering book: " + ex.getMessage());
        }
        
        return newBook;
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