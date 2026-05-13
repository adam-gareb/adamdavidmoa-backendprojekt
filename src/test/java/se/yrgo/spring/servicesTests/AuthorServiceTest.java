package se.yrgo.spring.servicesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.transaction.Transactional;
import se.yrgo.spring.dataaccess.AuthorNotFoundException;
import se.yrgo.spring.domain.Author;
import se.yrgo.spring.services.author.AuthorService;
import se.yrgo.spring.services.book.BookService;

// Adam
// Integration tests for AuthorService to test the methods inside it

@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "/other-tiers.xml", "/datasource-test.xml" })
@Transactional
public class AuthorServiceTest {
    @Autowired
    private AuthorService authors;

    @Test
    public void testAddAuthor() {
        authors.addAuthor("12345", "Johnson");
        authors.addAuthor("23456", "Charleston");

        int authorsInDb = authors.getAllAuthors().size();
        assertEquals(2, authorsInDb, "Two added books in database!");
    }

    @Test
    public void testFindingNonExistingAuthor() throws AuthorNotFoundException{
        assertThrows(AuthorNotFoundException.class, () -> {
            Author foundAuthor = authors.findAuthorByName("Non existing name");
        });
    }

    @Test
    public void testDeleteAuthor() {
        Author author = authors.addAuthor("55555", "Mathias");

        authors.deleteAuthor(author);

        assertThrows(AuthorNotFoundException.class, () -> {
            authors.findAuthorByName("Mathias");
        });
    }

    @Test
    public void testFindAuthorByName() throws AuthorNotFoundException {
        authors.addAuthor("99999", "Carl");

        Author author = authors.findAuthorByName("Carl");

        assertEquals(authors.findAuthorByName("Carl"), author);
    }

}
