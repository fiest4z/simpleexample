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

    public User findByEmail(String email) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.userEmail = :email",
                        User.class
                )
                .setParameter("email", email)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }


    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        if (user == null) {
            throw new IllegalArgumentException("Пользователь с id=" + id + " не найден");
        }

        // Проверка на отзывы
        Long reviewCount = entityManager.createQuery(
                        "SELECT COUNT(r) FROM Review r WHERE r.user.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();

        if (reviewCount > 0) {
            throw new IllegalStateException("Нельзя удалить пользователя, который оставил отзывы");
        }

        // Проверка на watchlist
        Long watchlistCount = entityManager.createQuery(
                        "SELECT COUNT(w) FROM Watchlist w WHERE w.user.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();

        if (watchlistCount > 0) {
            throw new IllegalStateException("Нельзя удалить пользователя, у которого есть записи в watchlist");
        }

        entityManager.remove(user);
        entityManager.flush();
    }

}
