package se.yrgo.spring.dataaccess;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.domain.Loan;
import se.yrgo.spring.domain.User;

public class LoanDaoJPAImpl implements LoanDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Loan> getAllLoans() {
        return em.createQuery("SELECT l from Loan l", Loan.class).getResultList();
    }

    @Override
    public List<Loan> findByUser(String userId) {
        return em.createQuery("SELECT l FROM Loan l JOIN l.users u WHERE u.userId = :userId", Loan.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Loan addLoan(Book book, Date startDate, Date dueDate, User user) {
        Loan loan = new Loan(book, startDate, dueDate, user);
        em.persist(loan);
        return loan;
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
