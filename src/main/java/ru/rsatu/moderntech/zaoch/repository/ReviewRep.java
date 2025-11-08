package ru.rsatu.moderntech.zaoch.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ru.rsatu.moderntech.zaoch.pojo.entity.Review;

import java.util.List;

@ApplicationScoped
public class ReviewRep {

    @Inject
    EntityManager entityManager;

    public List<Review> load() {
        return entityManager.createQuery("from Review r order by r.id ASC", Review.class)
                .getResultList();
    }

    @Transactional
    public void save(Review db_model) {
        if (db_model.getId() != null) {
            entityManager.merge(db_model);
        } else {
            db_model.setId(null);
            entityManager.persist(db_model);
        }
        entityManager.flush();
    }

    public Review findById(Long id) {
        return entityManager.find(Review.class, id);
    }

    @Transactional
    public void delete(Long id) {
        Review review = findById(id);
        if (review != null) {
            entityManager.remove(review);
            entityManager.flush();
        }
    }
}
