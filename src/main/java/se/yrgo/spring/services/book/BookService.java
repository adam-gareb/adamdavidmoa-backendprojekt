package se.yrgo.spring.services.book;

import java.util.*;
import se.yrgo.spring.dataaccess.*;
import se.yrgo.spring.domain.*;

// Moa
// Service interface for handling books. Defines necessary methods.

public interface BookService {
    public List<Book> getAllBooksByAuthor(String author);

    public Book getBookByIsbn(String isbn) throws BookNotFoundException;

    public List<Book> getEntireCatalogue();

    public Book registerNewBook(String isbn, String title, Set<Author> author);

    public void deleteFromStock(String isbn);

    public void updateBook(Book book);
}
