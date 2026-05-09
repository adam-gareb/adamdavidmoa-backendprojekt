package se.yrgo.spring.dataaccess;

import java.util.Date;
import java.util.List;
import java.util.Set;

import se.yrgo.spring.domain.*;

public interface LoanDao {

    public List<Loan> getAllLoans();

    public Loan findByUser(String userId);

    public void addLoan(String loandId, Book book, Date startDate, Date dueDate, User user);

    public Loan deleteLoan(String loanId);

    public void updateLoan(String loanId, Date dueDate);

}