package se.yrgo.spring.domain;

import java.util.*;
import java.util.stream.*;

import jakarta.persistence.*;

// Moa

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String isbn;
    private String title;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Author> authors;

    @Column(nullable = false)
    private boolean available = true;

    public Book() {
    }

    public Book(String isbn, String title, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.authors = new HashSet<>();
        this.available = true;
        addAuthor(author);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        String authorNames = authors.stream()
                .map(Author::getName)
                .collect(Collectors.joining(", "));

        String authorId = authors.stream()
                .map(Author::getAuthorId)
                .collect(Collectors.joining(", "));

        return String.format("""
                Bok ISBN: %s
                Titel: %s
                Författare: %s
                Författare ID: %s
                """, isbn, title, authorNames, authorId);
    }

}
