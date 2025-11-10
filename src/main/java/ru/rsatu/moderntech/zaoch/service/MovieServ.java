package ru.rsatu.moderntech.zaoch.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ru.rsatu.moderntech.zaoch.mapper.MovieMapper;
import ru.rsatu.moderntech.zaoch.pojo.dto.MovieSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.MovieView;
import ru.rsatu.moderntech.zaoch.repository.MovieRep;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MovieServ {
    @Inject
    MovieMapper mapper;

    @Inject
    MovieRep rep;

    public List<MovieView> load() {
        return rep.load().stream().map(mapper::toMovieView).collect(Collectors.toList());
    }

    public void save(MovieSave model) {
        rep.save(mapper.toMovie(model));
    }
    public void delete(Long id) {
        rep.delete(id);
    }
}
