package org.java.web.service;

import org.java.web.dao.UserDao;
import org.java.web.dao.UserDaoImpl;
import org.java.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void createNewUser(User user) {
        userDao.createNewUser(user);
    }

    @Transactional
    @Override
    public User readUser(long id) {
        return userDao.readUser(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> usersList() {
        return userDao.usersList();
    }

    @Override
    public User show(int id) {
        return userDao.show(id);
    }

}
