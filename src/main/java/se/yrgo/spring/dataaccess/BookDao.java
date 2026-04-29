package se.yrgo.spring.dataaccess;

import java.util.List;

import se.yrgo.spring.domain.Book;

public interface BookDao {
    public List<Book> getEntireCatalogue();
    public Book findByIsbn(String isbn) throws BookNotFoundException;
    public void registerNewBook(Book newBook);
    public void delete(Book redundantBook);
    public void updateBook(Book book);
    public List<Book> findBooksByAuthor(String author);
}
