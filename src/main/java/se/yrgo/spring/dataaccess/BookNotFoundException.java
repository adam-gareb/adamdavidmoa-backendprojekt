package se.yrgo.spring.dataaccess;

// Moa

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
