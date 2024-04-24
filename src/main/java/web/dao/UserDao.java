package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> index() {
        return entityManager.createQuery("select c from User c", User.class)
                .getResultList();
    }

    public User indexCount(int id) {
        return entityManager.find(User.class, id);
    }
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }
    @Transactional
    public void update(int id, User updated) {
        User userToBeUpdated = entityManager.find(User.class, id);

        userToBeUpdated.setName(updated.getName());
        userToBeUpdated.setLastname(updated.getLastname());
        userToBeUpdated.setAge(updated.getAge());
        userToBeUpdated.setId(updated.getId());
    }
    @Transactional
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
