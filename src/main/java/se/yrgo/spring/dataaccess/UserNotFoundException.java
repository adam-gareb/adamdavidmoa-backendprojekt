package se.yrgo.spring.dataaccess;

// Adam
// An exception for handling if you cannot find a User in the log file
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
