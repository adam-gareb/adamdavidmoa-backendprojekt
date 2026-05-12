package se.yrgo.spring.dataaccess;

import org.hsqldb.persist.RowInsertInterface.modes;

public class LoanNotFoundException extends Exception {
    public LoanNotFoundException(String message){
        super(message);
    }
}
