package org.java.web.service;

import org.java.web.model.User;

import java.util.List;

public interface UserService  {
    void createNewUser(User user);
    User readUser(long id);
    void updateUser(User user);
    void deleteUser(long id);
    List<User> usersList();
    User show(int id);

}
