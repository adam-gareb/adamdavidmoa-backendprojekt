package se.yrgo.spring.servicesTests;

import se.yrgo.spring.dataaccess.BookNotFoundException;
import se.yrgo.spring.domain.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    public void testGetAllBooksByAuthor() {

    }

    // We add a book (and author) to the database and test if we can find it with
    // title and isbn. We also test if the created book is null.
    @Test
    public void getBookByIsbn() throws BookNotFoundException {
        Author author = new Author();
        author.setName("Neil Gaiman");

        Set<Author> authors = new HashSet<>();
        authors.add(author);

        service.registerNewBook("10", "Good Omens", authors);

        Book testBook = service.getBookByIsbn("10");

        assertNotNull(testBook);
        assertEquals("Good Omens", testBook.getTitle());
        assertEquals("10", testBook.getIsbn());
    }

    // We add a list of books to the database and test if the size of list is
    // accurate.
    @Test
    public void getEntireCatalogue() {
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

    @Test
    public void registerNewBook() throws BookNotFoundException {

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

    @Test
    public void deleteFromStock() {

        Author author = new Author();
        author.setName("Faulty Author");

        Set<Author> authors = new HashSet<>();
        authors.add(author);

        service.registerNewBook("15", "Faulty Book", authors);
        service.deleteFromStock("15");

        service.getBookByIsbn("15");

    }

    @Test
    public void updateBook() {

    }

}
