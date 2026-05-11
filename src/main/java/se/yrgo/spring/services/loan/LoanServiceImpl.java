package se.yrgo.spring.services.loan;

import java.util.*;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import se.yrgo.spring.dataaccess.LoanDao;
import se.yrgo.spring.domain.*;

//Adam

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private LoanDao dao;

    public LoanServiceImpl(LoanDao dao){
        this.dao = dao;
    }

    @Override
    public Loan addLoan(String loanId, Set<Book> books, Date startDate, Date dueDate, User user) {
        Loan loan = new Loan(loanId, books, startDate, dueDate, user);

        dao.addLoan(loanId, books, startDate, dueDate, user);
        
        return loan;
    }

    @Override
    public void removeLoan(String loanId) {
        dao.deleteLoan(loanId);
    }

    @Override
    public List<Loan> getAllLoans() {
        return dao.getAllLoans();
    }

    @Override
    public void updateLoan(String loanId) {
        dao.updateLoan(loanId);
    }

    @Override
    public Loan findLoanByUserId(String userId) {
        return dao.findByUser(userId);
    }

    @Override
    public Loan findLoanById(String loanId){
        return dao.findByLoanId(loanId);
    }
    
}
