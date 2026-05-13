package se.yrgo.spring.dataaccess;

// Everyone
// An exception for handling if you cannot find a Loan in the log file
public class LoanNotFoundException extends Exception {
    public LoanNotFoundException(String message) {
        super(message);
    }
}
