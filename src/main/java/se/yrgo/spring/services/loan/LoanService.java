package se.yrgo.spring.services.loan;

import java.util.Date;
import java.util.List;

import se.yrgo.spring.domain.*;

//Adam

public interface LoanService {
    public void addLoan(String loanId, Book book, Date startDate, Date dueDate, User user);

    public void removeLoan(String loanId);

    public List<Loan> getAllLoans();

    public void updateLoan(String loanId, Date dueDate);

    public Loan findLoanById(String loanId);
}