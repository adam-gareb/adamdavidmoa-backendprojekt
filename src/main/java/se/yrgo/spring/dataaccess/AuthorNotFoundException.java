package se.yrgo.spring.dataaccess;

public class AuthorNotFoundException extends Exception {
    public AuthorNotFoundException(String message){
        super(message);
    }
}
