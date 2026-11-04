package org.example.dao;

import org.example.models.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);
    void removeUser(long id);
    void updateUser(long id, String name, int age);
    List<User> showAllUsers();


}
