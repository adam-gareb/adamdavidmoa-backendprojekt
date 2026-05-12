package se.yrgo.spring.dataaccess;

// Adam

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
