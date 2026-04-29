package se.yrgo.spring.dataaccess;

import java.util.*;

import jakarta.persistence.*;
import se.yrgo.spring.domain.*;

public class UserDaoJpaImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("FROM User u", User.class).getResultList();
    }

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public User findUserById(String id) {
        return em.createQuery("FROM User u WHERE userId = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<User> findUserByLastName(String lastName) {
        return em.createQuery("FROM User u WHERE lastname = :lastname", User.class)
                .setParameter("lastname", lastName)
                .getResultList();
    }

    @Override
    public User findUserByEmail(String email) {
        return em.createQuery("FROM User u WHERE email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

}
