package se.yrgo.spring.services.user;

import java.util.*;

import org.hsqldb.rights.*;

public interface UserService {
    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);

    public List<User> getAllUsers();

    public List<User> findUsersByLastName(String lastName);

    public User findUserById(String userId);

    public User findUserByEmail(String email);
}
