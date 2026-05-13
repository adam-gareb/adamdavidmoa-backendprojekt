package se.yrgo.spring.dataaccess;

import java.util.*;

import se.yrgo.spring.domain.*;

// David
// An interface that represents a service class that is used to manipulate the database
public interface UserDao {
    public List<User> getAllUsers();

    public void create(User user);

    public void delete(User user);

    public void update(User user);

    public User findUserById(String id) throws UserNotFoundException;

    public List<User> findUserByLastName(String lastName);

    public User findUserByEmail(String email) throws UserNotFoundException;

    public List<Loan> getAllUsersWithLoans();
}
