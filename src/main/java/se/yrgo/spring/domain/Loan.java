package se.yrgo.spring.domain;

import java.util.Date;

import jakarta.persistence.*;

// Adam
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String loanId;
    @OneToOne
    private Book book;
    private Date startDate;
    private Date dueDate;

    @ManyToOne
    private User user;

    public Loan() {
    }

    public Loan(String loanId, Book book, Date startDate, Date dueDate, User user) {
        this.loanId = loanId;
        this.book = book;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public void setBook(Book book) {
        this.book = book;
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
        return String.format("""
                User: %s
                Book(s) loaned: %s
                Start date:
                Due date:
                """, user, book, startDate, dueDate);
    }

}
