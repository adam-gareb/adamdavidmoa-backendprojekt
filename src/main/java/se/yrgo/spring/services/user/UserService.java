package se.yrgo.spring.services.user;

import java.util.*;

import se.yrgo.spring.domain.*;

// David
public interface UserService {
    public User addUser(String userId,
            String firstName,
            String lastName,
            String email,
            String address,
            String zip,
            String city);

    public User updateUser(String userId,
            String firstName,
            String lastName,
            String email,
            String address,
            String zip,
            String city);

    public void deleteUser(String userId);

    public List<User> getAllUsers();

    public List<User> findUsersByLastName(String lastName);

    public User findUserById(String userId);

    public User findUserByEmail(String email);
}
