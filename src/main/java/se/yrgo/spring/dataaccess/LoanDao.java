package se.yrgo.spring.dataaccess;

import java.util.Date;
import java.util.List;

import se.yrgo.spring.domain.*;

public interface LoanDao {

    public List<Loan> getAllLoans();

    public List<Loan> findByUser(String userId);

    public Loan addLoan(String loandId, Book book, Date startDate, Date dueDate, User user);

    public Loan deleteLoan(String loanId, Book book, Date startDate, Date dueDate, User user);

    public Loan updateLoan(String loanId, Book book, Date startDate, Date dueDate, User user);

}