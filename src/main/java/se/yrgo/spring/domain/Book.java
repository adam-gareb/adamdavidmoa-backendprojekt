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

    public Book() {
    }

    public Book(String isbn, String title, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.authors = new HashSet<>();
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

    @Override
    public String toString() {
        String authorNames = authors.stream()
            .map(Author::getName)
            .collect(Collectors.joining(", "));

        return String.format("""
                Book isbn: %s
                Book title: %s
                Author: %s
                """, isbn, title, authorNames);
    }

}
