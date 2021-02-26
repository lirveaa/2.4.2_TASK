package org.java.web.dao;

import org.java.web.model.User;

import java.util.List;


public interface UserDao {
    void createNewUser(User user);
    User readUser(long id);
    void updateUser(User user);
    void deleteUser(long id);
    List<User> usersList();
    User show(int id);
}
