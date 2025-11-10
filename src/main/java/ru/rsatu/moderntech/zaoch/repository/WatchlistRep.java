package ru.rsatu.moderntech.zaoch.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ru.rsatu.moderntech.zaoch.pojo.entity.Watchlist;

import java.util.List;

@ApplicationScoped
public class WatchlistRep {

    @Inject
    EntityManager entityManager;

    public List<Watchlist> load() {
        return entityManager.createQuery("from Watchlist w order by w.id ASC", Watchlist.class)
                .getResultList();
    }

    @Transactional
    public void save(Watchlist db_model) {
        if (db_model.getId() != null) {
            entityManager.merge(db_model);
        } else {
            db_model.setId(null);
            entityManager.persist(db_model);
        }
        entityManager.flush();
    }

    public Watchlist findById(Long id) {
        return entityManager.find(Watchlist.class, id);
    }

    @Transactional
    public void delete(Long id) {
        Watchlist watchlist = findById(id);
        if (watchlist != null) {
            entityManager.remove(watchlist);
            entityManager.flush();
        }
    }
    public boolean existsByUserAndMovie(Long userId, Long movieId, Long ignoreId) {
        String query = "select count(w) from Watchlist w where w.user.id = :userId and w.movie.id = :movieId";
        if (ignoreId != null) {
            query += " and w.id <> :ignoreId";
        }
        var q = entityManager.createQuery(query, Long.class)
                .setParameter("userId", userId)
                .setParameter("movieId", movieId);

        if (ignoreId != null) {
            q.setParameter("ignoreId", ignoreId);
        }

        Long count = q.getSingleResult();
        return count > 0;
    }


}
