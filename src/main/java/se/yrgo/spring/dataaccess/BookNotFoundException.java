package se.yrgo.spring.dataaccess;

// Everyone
// An exception for handling if you cannot find a Book in the log file
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
