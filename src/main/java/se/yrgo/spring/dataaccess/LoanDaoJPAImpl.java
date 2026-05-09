package se.yrgo.spring.dataaccess;

import java.util.Date;
import java.util.List;

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
        return em.createQuery(
                "SELECT l FROM Loan l JOIN l.user u WHERE u.userId = :userId",
                Loan.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Loan addLoan(String loanId, Book book, Date startDate, Date dueDate, User user) {
        Loan loan = new Loan(loanId, book, startDate, dueDate, user);
        em.persist(loan);
        return loan;
    }

    @Override
    public Loan deleteLoan(String loanId, Book book, Date startDate, Date dueDate, User user) {
        Loan loan = em.createQuery(
                "SELECT l FROM Loan l WHERE l.loandId = :loandId",
                Loan.class)
                .setParameter("loandId", loanId)
                .getSingleResult();

        em.remove(loan);
        return loan;
    }

    @Override
    public Loan updateLoan(String loanId, Book book, Date startDate, Date dueDate, User user) {
        Loan loan = em.createQuery("SELECT l FROM Loan l WHERE l.loanId = :loanId",
                Loan.class)
                .setParameter("loanId", loanId)
                .getSingleResult();

        em.merge(loan);
        return loan;
    }

}
