package se.yrgo.spring.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

// Adam
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String authorId;
    private String name;
    
    @ManyToMany
    private Set<Book> allBooks;

    public Author(){}

    public Author(String authorId, String name, Set<Book> allBooks) {
        this.authorId = authorId;
        this.name = name;
        this.allBooks = allBooks;
    }

    public Author(String authorId, String name){
        this.authorId = authorId;
        this.name = name;
    }


    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(Set<Book> allBooks) {
        this.allBooks = allBooks;
    }

    @Override
    public String toString() {
        return "Author name: " + name + "\nBooks: " + allBooks;
    }
    
}
