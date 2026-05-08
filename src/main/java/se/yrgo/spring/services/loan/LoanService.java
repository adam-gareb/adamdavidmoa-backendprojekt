package se.yrgo.spring.services.loan;

import java.sql.Date;
import java.util.Set;

import se.yrgo.spring.domain.*;

//Adam

public interface LoanService {
    public Loan addLoan(String loanId, Book book, Date startDate, Date dueDate, User user);

    public void removeLoan(String loanId);

    public Set<Loan> getAllLoans();

    public void updateLoan(String loanId);

    public Loan findLoanById(String loanId);
}