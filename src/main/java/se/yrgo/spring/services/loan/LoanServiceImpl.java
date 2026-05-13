package se.yrgo.spring.services.loan;

import java.util.*;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import se.yrgo.spring.dataaccess.LoanDao;
import se.yrgo.spring.dataaccess.LoanNotFoundException;
import se.yrgo.spring.domain.*;

// Adam
// Implementation class for LoanService, to implements methods to work together with the DAO
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private LoanDao dao;

    public LoanServiceImpl(LoanDao dao){
        this.dao = dao;
    }

    @Override
    public Loan addLoan(String loanId, Set<Book> books, Date startDate, Date dueDate, User user) {        
        return dao.addLoan(loanId, books, startDate, dueDate, user);
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
    public Loan findLoanById(String loanId) throws LoanNotFoundException{
        return dao.findByLoanId(loanId);
    }
    
}
