package se.yrgo.spring.domain;

import java.util.Date;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((loanId == null) ? 0 : loanId.hashCode());
        result = prime * result + ((books == null) ? 0 : books.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Loan other = (Loan) obj;
        if (loanId == null) {
            if (other.loanId != null)
                return false;
        } else if (!loanId.equals(other.loanId))
            return false;
        if (books == null) {
            if (other.books != null)
                return false;
        } else if (!books.equals(other.books))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (dueDate == null) {
            if (other.dueDate != null)
                return false;
        } else if (!dueDate.equals(other.dueDate))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String titles = books.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(", "));

        return String.format("""
                Lån ID: %s
                Användare ID: %s
                Avändare namn: %s %s
                Böcker lånade: %s
                Start datum: %s
                Inlämningsdatum: %s
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
