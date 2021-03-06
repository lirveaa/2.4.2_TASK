package org.java.web.dao;

import org.java.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserDao  {
    void createNewUser(User user);
    User readUser(long id);
    void updateUser(User user);
    void deleteUser(long id);
    List<User> usersList();
}
