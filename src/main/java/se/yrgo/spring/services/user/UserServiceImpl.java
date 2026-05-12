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
    public User addUser(String userId,
            String firstName,
            String lastName,
            String email,
            String password,
            String address,
            String zip,
            String city) {
        try {
            User user = new User(userId, firstName, lastName, email, password, address, zip, city);
            return dao.create(user);
        } catch (Exception ex) {
            System.err.println("Something went wrong with adding a user: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public User updateUser(String userId,
            String firstName,
            String lastName,
            String email,
            String password,
            String address,
            String zip,
            String city) {
        try {
            User user = dao.findUserById(userId); // hämta befintlig
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setAddress(address);
            user.setZip(zip);
            user.setCity(city);
            dao.update(user); // nu har den rätt id → UPDATE
            return user;
        } catch (Exception ex) {
            System.err.println("Something went wrong with updating a user: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void deleteUser(String userId) {
        try {
            User user = dao.findUserById(userId);
            dao.delete(user);
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
    public User findUserById(String userId) throws UserNotFoundException {
        return dao.findUserById(userId);
    }

    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        return dao.findUserByEmail(email);
    }

}
