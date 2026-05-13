package se.yrgo.spring.servicesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import se.yrgo.spring.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import jakarta.transaction.Transactional;
import se.yrgo.spring.dataaccess.AuthorNotFoundException;
import se.yrgo.spring.dataaccess.LoanNotFoundException;
import se.yrgo.spring.domain.Author;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.domain.Loan;
import se.yrgo.spring.services.author.AuthorService;
import se.yrgo.spring.services.book.BookService;
import se.yrgo.spring.services.loan.LoanService;
import se.yrgo.spring.services.user.UserService;

// Adam
// Integration tests for LoanService to test the methods inside it

@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "/other-tiers.xml", "/datasource-test.xml" })
@Transactional
public class LoanServiceTest {
    @Autowired
    private LoanService loans;

    @Autowired
    private BookService books;

    @Autowired
    private AuthorService authors;

    @Autowired
    private UserService users;

    @Test
    public void testAddLoan() {
        Author author = authors.addAuthor("111", "Johan");

        Set<Author> authorsSet = new HashSet<>();
        authorsSet.add(author);

        Book book = books.registerNewBook("123", "Book", authorsSet);

        Set<Book> booksSet = new HashSet<>();
        booksSet.add(book);

        User user = users.addUser("222", "Göran", "Göransson", "göran@outlook.com", "123", "Gatan 11", "421 99",
                "Göteborg");

        Date startDate = new Date();
        Date dueDate = new Date(startDate.getTime() + 14L * 24 * 60 * 60 * 1000);

        loans.addLoan("1", booksSet, startDate, dueDate, user);

        int loansInDb = loans.getAllLoans().size();

        assertEquals(1, loansInDb, "1 added loan in database!");
    }

    @Test
    public void testFindingNonExistingLoan() throws LoanNotFoundException {
        assertThrows(LoanNotFoundException.class, () -> {
            Loan foundLoan = loans.findLoanById("Non existing ID");
        });
    }

    @Test
    public void testRemoveLoan() {
        Author author = authors.addAuthor("111", "Johan");

        Set<Author> authorsSet = new HashSet<>();
        authorsSet.add(author);

        Book book = books.registerNewBook("123", "Book", authorsSet);

        Set<Book> booksSet = new HashSet<>();
        booksSet.add(book);

        User user = users.addUser("222", "Göran", "Göransson", "göran@outlook.com", "123", "Gatan 11", "421 99",
                "Göteborg");

        Date startDate = new Date();
        Date dueDate = new Date(startDate.getTime() + 14L * 24 * 60 * 60 * 1000);

        loans.addLoan("1", booksSet, startDate, dueDate, user);

        loans.removeLoan("1");

        assertThrows(LoanNotFoundException.class, () -> {
            loans.findLoanById("1");
        });
    }

    @Test
    public void testUpdateLoan() throws LoanNotFoundException {
        Author author = authors.addAuthor("111", "Johan");

        Set<Author> authorsSet = new HashSet<>();
        authorsSet.add(author);

        Book book = books.registerNewBook("123", "Book", authorsSet);

        Set<Book> booksSet = new HashSet<>();
        booksSet.add(book);

        User user = users.addUser("222", "Göran", "Göransson", "göran@outlook.com", "123", "Gatan 11", "421 99",
                "Göteborg");

        Date startDate = new Date();
        Date dueDate = new Date(startDate.getTime() + 14L * 24 * 60 * 60 * 1000);

        Loan loan = loans.addLoan("100", booksSet, startDate, dueDate, user);
        Date originalDueDate = loan.getDueDate();

        loans.updateLoan("100");

        Loan updatedLoan = loans.findLoanById("100");
        Date expectedDueDate = new Date(originalDueDate.getTime() + 14L * 24 * 60 * 60 * 1000);

        assertEquals(expectedDueDate, updatedLoan.getDueDate());
    }

    @Test
    public void testFindLoanByLoanId() throws LoanNotFoundException {
        Author author = authors.addAuthor("111", "Johan");

        Set<Author> authorsSet = new HashSet<>();
        authorsSet.add(author);

        Book book = books.registerNewBook("123", "Book", authorsSet);

        Set<Book> booksSet = new HashSet<>();
        booksSet.add(book);

        User user = users.addUser("222", "Göran", "Göransson", "göran@outlook.com", "123", "Gatan 11", "421 99",
                "Göteborg");

        Date startDate = new Date();
        Date dueDate = new Date(startDate.getTime() + 14L * 24 * 60 * 60 * 1000);

        Loan loan = loans.addLoan("100", booksSet, startDate, dueDate, user);

        Loan foundLoan = loans.findLoanById("100");

        assertEquals(loan, foundLoan);
    }

    @Test
    public void testFindLoanByUserId() {
        Author author = authors.addAuthor("111", "Johan");

        Set<Author> authorsSet = new HashSet<>();
        authorsSet.add(author);

        Book book = books.registerNewBook("123", "Book", authorsSet);

        Set<Book> booksSet = new HashSet<>();
        booksSet.add(book);

        User user = users.addUser("222", "Göran", "Göransson", "göran@outlook.com", "123", "Gatan 11", "421 99",
                "Göteborg");

        Date startDate = new Date();
        Date dueDate = new Date(startDate.getTime() + 14L * 24 * 60 * 60 * 1000);

        Loan loan = loans.addLoan("100", booksSet, startDate, dueDate, user);

        Loan foundLoans = loans.findLoanByUserId("222");

        assertEquals(loan, foundLoans);
    }
}
