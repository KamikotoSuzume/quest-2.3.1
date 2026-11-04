package org.example.dao;

import org.example.models.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(long id) {
        Optional.ofNullable(entityManager.find(User.class, id))
                .ifPresent(entityManager::remove);
    }


    @Override
    public void updateUser(long id, String name, int age) {
        Optional.ofNullable(entityManager.find(User.class, id)).
                ifPresent(user -> {
                    user.setName(name);
                    user.setAge(age);
                });
    }

    @Override
    public List<User> showAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();

    }
}
