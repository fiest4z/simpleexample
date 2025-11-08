package ru.rsatu.moderntech.zaoch.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ru.rsatu.moderntech.zaoch.pojo.entity.User;

import java.util.List;

@ApplicationScoped
public class UserRep {

    @Inject
    EntityManager entityManager;


    public List<User> load() {
        return entityManager.createQuery("from User u order by u.username ASC", User.class).getResultList();
    }

    @Transactional
    public void save(User db_model) {
        if (db_model.getId() != null) {
            entityManager.merge(db_model);
        } else {
            db_model.setId(null);
            entityManager.persist(db_model);
        }
        entityManager.flush();
    }
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
            entityManager.flush();
        }
    }
}
