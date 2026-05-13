package se.yrgo.spring.dataaccess;

import java.util.*;

import se.yrgo.spring.domain.*;

// Adam
// A DAO interface for Loan, for the service class LoanService
public interface LoanDao {

    public List<Loan> getAllLoans();

    public Loan findByUser(String userId);

    public Loan findByLoanId(String loanId) throws LoanNotFoundException;

    public Loan addLoan(String loandId, Set<Book> books, Date startDate, Date dueDate, User user);

    public void deleteLoan(String loanId);

    public void updateLoan(String loanId);

}