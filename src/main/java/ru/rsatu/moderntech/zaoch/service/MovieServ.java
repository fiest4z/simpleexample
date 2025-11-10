package ru.rsatu.moderntech.zaoch.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ru.rsatu.moderntech.zaoch.mapper.MovieMapper;
import ru.rsatu.moderntech.zaoch.pojo.dto.MovieSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.MovieView;
import ru.rsatu.moderntech.zaoch.pojo.entity.Movie;
import ru.rsatu.moderntech.zaoch.repository.MovieRep;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MovieServ {
    @Inject
    MovieMapper mapper;

    @Inject
    MovieRep rep;
    EntityManager entityManager;

    public List<MovieView> load() {
        return rep.load().stream().map(mapper::toMovieView).collect(Collectors.toList());
    }

    public void save(MovieSave model) {
        rep.save(mapper.toMovie(model));
    }

    public void delete(Long id) {
        rep.delete(id);
    }

    public MovieView findById(Long id) {
        Movie movie = rep.findById(id);
        return mapper.toMovieView(movie);
    }

    @Transactional
    public void update(MovieSave model) {
        Movie existing = rep.findById(model.getId());
        if (existing != null) {
            existing.setTitle(model.getTitle());
            existing.setReleaseYear(model.getReleaseYear());
            existing.setGenre(model.getGenre());
            existing.setDescription(model.getDescription());
            rep.save(existing); // или entityManager.merge(existing);
        } else {
            save(model); // если не найден, создать новый
        }
    }
}
