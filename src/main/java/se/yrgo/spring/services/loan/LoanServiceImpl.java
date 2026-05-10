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
    public void addLoan(String loanId, Book book, Date startDate, Date dueDate, User user) {
        dao.addLoan(loanId, book, startDate, dueDate, user);
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
    public void updateLoan(String loanId, Date dueDate) {
        dao.updateLoan(loanId, dueDate);
    }

    @Override
    public Loan findLoanById(String loanId) {
        return dao.findByUser(loanId);
    }
    
}
