package se.yrgo.spring.domain;

import java.sql.Date;

import jakarta.persistence.*;

// Adam
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Book book;
    private Date startDate;
    private Date dueDate;

    @ManyToOne
    private User user;

    public Loan() {
    }

    public Loan(Book book, Date startDate, Date dueDate, User user) {
        this.book = book;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
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
        return "Loan [book=" + book + ", startDate=" + startDate + ", dueDate=" + dueDate + "]";
    }

}
