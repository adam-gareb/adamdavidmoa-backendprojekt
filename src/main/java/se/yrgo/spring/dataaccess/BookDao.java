package se.yrgo.spring.dataaccess;

import java.util.*;

import se.yrgo.spring.domain.Book;

// Moa

public interface BookDao {
    public List<Book> getEntireCatalogue();
    public Book findByIsbn(String isbn) throws BookNotFoundException;
    public void registerNewBook(Book newBook);
    public void delete(Book redundantBook);
    public List<Book> findBooksByAuthor(String author);
    public void updateBook(Book book);
}
