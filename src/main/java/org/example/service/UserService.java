package org.example.service;

import org.example.models.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void removeUser(long id);

    void updateUser(long id, String name, int age);

    User getUser(long id);

    List<User> showAllUsers();
}
