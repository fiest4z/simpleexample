package ru.rsatu.moderntech.zaoch.mapper;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.moderntech.zaoch.pojo.dto.WatchlistSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.WatchlistView;
import ru.rsatu.moderntech.zaoch.pojo.entity.Movie;
import ru.rsatu.moderntech.zaoch.pojo.entity.User;
import ru.rsatu.moderntech.zaoch.pojo.entity.Watchlist;


@Mapper(componentModel = "jakarta")
public abstract class WatchlistMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "movieTitle", source = "movie.title")
    public abstract WatchlistView toWatchlistView(Watchlist from);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "movie", ignore = true)
    public abstract Watchlist toWatchlist(WatchlistSave from);

    @AfterMapping
    protected void updateWatchlistAfterMapping(@MappingTarget Watchlist db_model, WatchlistSave from) {
        if (from.getUserId() != null) {
            db_model.setUser(entityManager.getReference(User.class, from.getUserId()));
        }
        if (from.getMovieId() != null) {
            db_model.setMovie(entityManager.getReference(Movie.class, from.getMovieId()));
        }
    }
}
