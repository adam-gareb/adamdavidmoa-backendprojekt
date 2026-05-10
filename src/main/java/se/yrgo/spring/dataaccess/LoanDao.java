package se.yrgo.spring.dataaccess;

import java.util.*;


import se.yrgo.spring.domain.*;

public interface LoanDao {

    public List<Loan> getAllLoans();

    public Loan findByUser(String userId);

    public Loan addLoan(String loandId, Set<Book> books, Date startDate, Date dueDate, User user);

    public Loan deleteLoan(String loanId);

    public void updateLoan(String loanId, Date dueDate);

}