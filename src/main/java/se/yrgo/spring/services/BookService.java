package se.yrgo.spring.services;

import java.util.List;

import se.yrgo.spring.dataaccess.BookNotFoundException;
import se.yrgo.spring.domain.Book;

public interface BookService {
    public List<Book> getAllBooksByAuthor(String author);

    public Book getBookByIsbn(String isbn) throws BookNotFoundException;

    public List<Book> getEntireCatalogue();

    public void registerNewBook(Book newBook);

    public void updateBook (Book book);

    public void deleteFromStock(Book book);

}
