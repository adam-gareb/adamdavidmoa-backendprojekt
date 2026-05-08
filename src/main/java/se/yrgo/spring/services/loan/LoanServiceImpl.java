package se.yrgo.spring.services.loan;

import java.sql.Date;
import java.util.Set;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.domain.Loan;
import se.yrgo.spring.domain.User;

//Adam

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Override
    public Loan addLoan(String loanId, Book book, Date startDate, Date dueDate, User user) {

    }

    @Override
    public void removeLoan(String loanId) {

    }

    @Override
    public Set<Loan> getAllLoans() {
  
    }

    @Override
    public void updateLoan(String loanId) {

    }

    @Override
    public Loan findLoanById(String loanId) {

    }
    
}
