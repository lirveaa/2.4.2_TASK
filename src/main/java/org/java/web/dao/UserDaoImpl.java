package org.java.web.dao;

import org.java.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Transactional
@Repository
@EnableTransactionManagement
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    @Autowired
    EntityManager em;

    @Override
    public void createNewUser(User user) {
        em.persist(user);
    }

    @Override
    public User readUser(long id) {
        return (User)em.createQuery("from User where id=:id")
                .setParameter("id",id)
                .getSingleResult();
    }



    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = readUser(id);
        em.remove(user);
    }

    @Override
    public List<User> usersList() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u",User.class);
        return query.getResultList();
    }

    @Override
    public User show(int id) {
        return usersList().stream().filter(user -> user.getId() == id).findAny().orElse(null);

    }
}
