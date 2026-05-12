package se.yrgo.spring.dataaccess;

import java.util.*;

import org.springframework.stereotype.*;

import jakarta.persistence.*;
import se.yrgo.spring.domain.*;

// David

@Repository
public class UserDaoJpaImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public void delete(User user) {
        User managed = em.merge(user);
        em.remove(managed);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public User findUserById(String id) {
        return em.createQuery("SELECT u FROM User u WHERE u.userId = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<User> findUserByLastName(String lastName) {
        return em.createQuery("SELECT u FROM User u WHERE u.lastName = :lastName", User.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException("Ingen användare hittades med mail: " + email);
        }
    }

    @Override
    public List<Loan> getAllUsersWithLoans() {
        return em.createQuery("SELECT l FROM Loan l JOIN l.user u", Loan.class).getResultList();
    }

    // Implementera join metoder också

}
