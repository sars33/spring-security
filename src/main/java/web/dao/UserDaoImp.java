package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> showAll() {
        return entityManager.createQuery("From User user").getResultList();

    }

    @Override
    public void addAndSave(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByName(String username) {
        return entityManager.createQuery("From User where username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();

    }

    @Override
    public Role getUserByRoleName(String name) {
        return entityManager.createQuery("From Role where name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
