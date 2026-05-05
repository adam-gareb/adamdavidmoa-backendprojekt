package se.yrgo.spring.services.user;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import se.yrgo.spring.dataaccess.*;
import se.yrgo.spring.domain.*;

// David
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void addUser(String userId,
            String firstName,
            String lastName,
            String email,
            String address,
            String zip,
            String city) {
        try {
            User user = new User(userId, firstName, lastName, email, address, zip, city);
            dao.create(user);
        } catch (Exception ex) {
            System.err.println("Something went wrong with adding a user: " + ex.getMessage());
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
        try {
            User user = new User(userId, firstName, lastName, email, address, zip, city);
            dao.update(user);
        } catch (Exception ex) {
            System.err.println("Something went wrong with updating a user: " + ex.getMessage());
        }
    }

    @Override
    public void deleteUser(String userId) {
        try {
            User user = dao.findUserById(userId);
            dao.create(user);
        } catch (Exception ex) {
            System.err.println("Something went wrong with deleting a user: " + ex.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public List<User> findUsersByLastName(String lastName) {
        return dao.findUserByLastName(lastName);
    }

    @Override
    public User findUserById(String userId) {
        return findUserById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return findUserByEmail(email);
    }

}
