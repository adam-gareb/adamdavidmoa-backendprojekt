package se.yrgo.spring.dataaccess;

import java.util.*;

import org.springframework.stereotype.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import se.yrgo.spring.domain.*;

@Repository
public class LoanDaoJPAImpl implements LoanDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Loan> getAllLoans() {
        return em.createQuery("SELECT l from Loan l", Loan.class).getResultList();
    }

    @Override
    public Loan findByUser(String userId) {
        Loan loan = em.createQuery(
                "SELECT l FROM Loan l JOIN l.user u WHERE u.userId = :userId",
                Loan.class)
                .setParameter("userId", userId)
                .getSingleResult();

        return loan;
    }

    @Override
    public void addLoan(String loanId, Book book, Date startDate, Date dueDate, User user) {
        Loan loan = new Loan(loanId, book, startDate, dueDate, user);
        em.persist(loan);
    }

    @Override
    public Loan deleteLoan(String loanId) {
        Loan loan = em.createQuery(
                "SELECT l FROM Loan l WHERE l.loandId = :loandId",
                Loan.class)
                .setParameter("loanId", loanId)
                .getSingleResult();

        em.remove(loan);
        return loan;
    }

    @Override
    public void updateLoan(String loanId, Date dueDate) {
        // Loan loan = em.createQuery("SELECT l FROM Loan l WHERE l.loanId = :loanId",
        // Loan.class)
        // .setParameter("loanId", loanId)
        // .getSingleResult();

        // Loan loan = em.createQuery("UPDATE Loan l SET ")
        // .setParameter("dueDate", dueDate)

        // em.merge(loan);

        Loan loan = em.createQuery("SELECT l FROM Loan l WHERE l.loanId = :loanId", Loan.class)
                .setParameter("loanId", loanId)
                .getSingleResult();

        Date newDueDate = new Date(loan.getDueDate().getTime() + 14L * 24 * 60 * 60 * 1000);
        loan.setDueDate(newDueDate);
    }

}
