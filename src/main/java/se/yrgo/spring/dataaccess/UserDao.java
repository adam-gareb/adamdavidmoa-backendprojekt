package se.yrgo.spring.dataaccess;

import java.util.*;

import se.yrgo.spring.domain.*;

public interface UserDao {
    public List<User> getAllUsers();

    public void create(User user);

    public void delete(User user);

    public void update(User user);

    public User findUserById(String id);

    public List<User> findUserByLastName(String lastName);

    public User findUserByEmail(String email);

    public List<Loan> getAllUsersWithLoans();
}
