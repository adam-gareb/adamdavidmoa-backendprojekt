package se.yrgo.spring.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.*;

// Adam
// Loan entity class, to use together with Service class, JPA and DAO
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String loanId;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Book> books;
    private Date startDate;
    private Date dueDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Loan() {
    }

    public Loan(String loanId, Set<Book> books, Date startDate, Date dueDate, User user) {
        this.loanId = loanId;
        this.books = books;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.user = user;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public void setBooks(Set<Book> book) {
        this.books = book;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        String titles = books.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(", "));

        return String.format("""
                Loan ID: %s
                User ID: %s
                User: %s %s
                Book(s) loaned: %s
                Start date: %s
                Due date: %s
                """,
                loanId,
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                titles,
                startDate,
                dueDate);
    }

}
