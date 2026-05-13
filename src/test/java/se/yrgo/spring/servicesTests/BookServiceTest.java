package se.yrgo.spring.servicesTests;

import se.yrgo.spring.dataaccess.BookNotFoundException;
import se.yrgo.spring.domain.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.transaction.annotation.*;

import se.yrgo.spring.services.book.BookService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "/other-tiers.xml", "/datasource-test.xml" })
@Transactional
public class BookServiceTest {

    @Autowired
    private BookService service;

    // Creating an author and a list of books connected. Then we test if the
    // author's book list is accurate in size.
    @Test
    public void testGetAllBooksByAuthor() {

        Author author = new Author();
        author.setName("Neil Gaiman");

        Set<Author> authors = new HashSet<>();
        authors.add(author);

        List<Book> existingBooks = List.of(
                service.registerNewBook("1", "Coraline", authors),
                service.registerNewBook("2", "Good Omens", authors),
                service.registerNewBook("3", "The Wolves in the Walls", authors));

        List<Book> findBooksByAuthor = service.getAllBooksByAuthor("Neil Gaiman");

        assertEquals(3, findBooksByAuthor.size());

    }

    // We add a book (and author) to the database and test if we can find it with
    // isbn. We also test if the created book is null.
    @Test
    public void testGetBookByIsbn() throws BookNotFoundException {
        Author author = new Author();
        author.setName("Neil Gaiman");

        Set<Author> authors = new HashSet<>();
        authors.add(author);

        service.registerNewBook("10", "Good Omens", authors);

        Book testBook = service.getBookByIsbn("10");

        assertNotNull(testBook);
        assertEquals("10", testBook.getIsbn());
    }

    // We add a list of books to the database and test if the size of list is
    // accurate.
    @Test
    public void testGetEntireCatalogue() {

        Author author = new Author();
        author.setName("Neil Gaiman");

        Set<Author> authors = new HashSet<>();
        authors.add(author);

        List<Book> existingBooks = List.of(
                service.registerNewBook("1", "Coraline", authors),
                service.registerNewBook("2", "Good Omens", authors),
                service.registerNewBook("3", "The Wolves in the Walls", authors));

        List<Book> findBooks = service.getEntireCatalogue();

        assertEquals(existingBooks.size(), findBooks.size());
        assertTrue(existingBooks.containsAll(findBooks));

    }

    // We add a book and test if it's saved correctly.
    @Test
    public void testRegisterNewBook() throws BookNotFoundException {

        Author author = new Author();
        author.setName("Tim Bowler");

        Set<Author> authors = new HashSet<>();
        authors.add(author);

        service.registerNewBook("20", "Blade", authors);

        Book saved = service.getBookByIsbn("20");

        assertNotNull(saved);
        assertEquals("Blade", saved.getTitle());
        assertEquals("20", saved.getIsbn());

    }

    // We add a book, then delete it and test if it's successful.
    @Test
    public void testDeleteFromStock() {

        Author author = new Author();
        author.setName("Tim Bowler");

        Set<Author> authors = new HashSet<>();
        authors.add(author);

        service.registerNewBook("20", "Blade", authors);
        service.deleteFromStock("20");

        assertThrows(BookNotFoundException.class, () -> {
            service.getBookByIsbn("20");
        });

    }

    // We add a book and update it's isbn, then testing to see if the new change is
    // accurate.
    @Test
    public void testUpdateBook() throws BookNotFoundException {

        Author author = new Author();
        author.setName("Emily Brontë");

        Set<Author> authors = new HashSet<>();
        authors.add(author);

        service.registerNewBook("24", "Wuthering Heights", authors);

        Book book = service.getBookByIsbn("24");
        book.setIsbn("25");

        service.updateBook(book);

        Book updated = service.getBookByIsbn("25");

        assertEquals("25", updated.getIsbn());

    }

}
