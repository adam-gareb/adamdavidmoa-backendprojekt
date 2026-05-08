package se.yrgo.spring.dataaccess;

import java.util.Date;
import java.util.List;

import se.yrgo.spring.domain.*;

public interface LoanDao {

    public List<Loan> getAllLoans();

    public List<Loan> findByUser(String userId);

    public Loan addLoan(Book book, Date startDate, Date dueDate, User user);

    public Loan deleteLoan(Book book, Date startDate, Date dueDate, User user);

    public Loan updateLoan(Book book, Date startDate, Date dueDate, User user);

}