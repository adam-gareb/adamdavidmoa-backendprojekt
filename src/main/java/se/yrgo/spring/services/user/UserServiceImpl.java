package se.yrgo.spring.services.user;

import java.util.*;

import org.springframework.stereotype.*;

import jakarta.persistence.*;
import se.yrgo.spring.dataaccess.*;
import se.yrgo.spring.domain.*;

// David
@Service
public class UserServiceImpl implements UserService {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");

    private UserDao dao;

    @Override
    public void addUser(String userId,
            String firstName,
            String lastName,
            String email,
            String address,
            String zip,
            String city) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            User user = new User(userId, firstName, lastName, email, address, zip, city);

            dao.create(user);

            tx.commit();
        } catch (Exception ex) {
            System.err.println("Something went wrong: " + ex.getMessage());
        }

    }

    @Override
    public void updateUser(String userId,
            String firstName,
            String lastName,
            String email,
            String address,
            String zip,
            String city) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            User user = new User(userId, firstName, lastName, email, address, zip, city);

            dao.update(user);

            tx.commit();
        } catch (Exception ex) {
            System.err.println("Something went wrong: " + ex.getMessage());
        }
    }

    @Override
    public void deleteUser(String userId) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            User user = dao.findUserById(userId);

            dao.delete(user);

            tx.commit();
        } catch (Exception ex) {
            System.err.println("Something went wrong: " + ex.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public List<User> findUsersByLastName(String lastName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUsersByLastName'");
    }

    @Override
    public User findUserById(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUserById'");
    }

    @Override
    public User findUserByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUserByEmail'");
    }

}
