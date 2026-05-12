package se.yrgo.spring.dataaccess;

import java.util.*;

import se.yrgo.spring.domain.*;

// David

public interface UserDao {
    public List<User> getAllUsers();

    public void create(User user);

    public void delete(User user);

    public void update(User user);

    public User findUserById(String userId) throws UserNotFoundException;

    public List<User> findUserByLastName(String lastName);

    public User findUserByEmail(String email) throws UserNotFoundException;

    public List<Loan> getAllUsersWithLoans();
}
