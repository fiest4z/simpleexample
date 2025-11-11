package ru.rsatu.moderntech.zaoch.repository;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ru.rsatu.moderntech.zaoch.pojo.entity.Movie;

import java.util.List;

@ApplicationScoped
public class MovieRep {

    @Inject
    EntityManager entityManager;

    public List<Movie> load() {
        return entityManager.createQuery("from Movie m order by m.title ASC", Movie.class).getResultList();
    }

    @Transactional
    public void save(Movie db_model) {
        if (db_model.getId() != null) {
            entityManager.merge(db_model);
        } else {
            db_model.setId(null);
            entityManager.persist(db_model);
        }
        entityManager.flush();
    }

    public Movie findById(Long id) {
        return entityManager.find(Movie.class, id);

    }

    @Transactional
    public void delete(Long id) {
        Movie movie = findById(id);
        if (movie == null) {
            throw new IllegalArgumentException("Фильм с id=" + id + " не найден");
        }

        // Проверяем, есть ли записи в Watchlist, где используется этот фильм
        Long count = entityManager.createQuery(
                        "SELECT COUNT(w) FROM Watchlist w WHERE w.movie.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();

        if (count > 0) {
            throw new IllegalStateException("Нельзя удалить фильм, который есть в списках пользователей");
        }

        entityManager.remove(movie);
        entityManager.flush();
    }



}
