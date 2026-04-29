package se.yrgo.spring.services.user;

import java.util.*;

import org.hsqldb.rights.*;

public interface UserService {
    public User addUser(String userId, String );

    public User updateUser(User user);

    public User deleteUser(User user);

    public List<User> getAllUsers();

    public List<User> findUsersByLastName(String lastName);

    public User findUserById(String userId);

    public User findUserByEmail(String email);
}
