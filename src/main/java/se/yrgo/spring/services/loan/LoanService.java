package se.yrgo.spring.services.loan;

import java.util.Date;
import java.util.List;
import java.util.Set;

import se.yrgo.spring.dataaccess.LoanNotFoundException;
import se.yrgo.spring.domain.*;

// Adam
// Service interface for Loan
public interface LoanService {
    public Loan addLoan(String loanId, Set<Book> books, Date startDate, Date dueDate, User user);

    public void removeLoan(String loanId);

    public List<Loan> getAllLoans();

    public void updateLoan(String loanId);

    public Loan findLoanByUserId(String userId);

    public Loan findLoanById(String loanId) throws LoanNotFoundException;
}