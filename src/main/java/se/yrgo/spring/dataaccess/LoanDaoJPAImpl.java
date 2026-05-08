package se.yrgo.spring.dataaccess;

import java.util.Date;
import java.util.List;

import se.yrgo.spring.domain.Book;
import se.yrgo.spring.domain.Loan;
import se.yrgo.spring.domain.User;

public class LoanDaoJPAImpl implements LoanDao {

    @Override
    public List<Loan> getAllLoans() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllLoans'");
    }

    @Override
    public User findByUser(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUser'");
    }

    @Override
    public Loan addLoan(Book book, Date startDate, Date dueDate, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLoan'");
    }

    @Override
    public Loan deleteLoan(Book book, Date startDate, Date dueDate, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteLoan'");
    }

    @Override
    public Loan updateLoan(Book book, Date startDate, Date dueDate, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateLoan'");
    }

}
