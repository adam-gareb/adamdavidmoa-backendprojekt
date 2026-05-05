package se.yrgo.spring.services.book;

import java.util.List;
import java.util.Set;

import se.yrgo.spring.dataaccess.BookNotFoundException;
import se.yrgo.spring.domain.Author;
import se.yrgo.spring.domain.Book;

// Moa

public interface BookService {
    public List<Book> getAllBooksByAuthor(String author);

    public Book getBookByIsbn(String isbn) throws BookNotFoundException;

    public List<Book> getEntireCatalogue();

    public void registerNewBook(String isbn, String title, Set<Author> authors);

    public void deleteFromStock(String isbn);

}
