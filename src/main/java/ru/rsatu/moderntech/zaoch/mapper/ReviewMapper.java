package ru.rsatu.moderntech.zaoch.mapper;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.moderntech.zaoch.pojo.dto.ReviewSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.ReviewView;
import ru.rsatu.moderntech.zaoch.pojo.entity.Movie;
import ru.rsatu.moderntech.zaoch.pojo.entity.Review;
import ru.rsatu.moderntech.zaoch.pojo.entity.User;

@Mapper(componentModel = "jakarta")
public abstract class ReviewMapper {

    @Inject
    EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "movieTitle", source = "movie.title")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "comment", source = "comment")
    public abstract ReviewView toReviewView(Review review);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "comment", source = "comment")
    public abstract Review toReview(ReviewSave dto);

    protected void setEntitiesAfterMapping(@MappingTarget Review review, ReviewSave dto) {
        if (dto.getUserId() != null) {
            review.setUser(entityManager.getReference(User.class, dto.getUserId()));
        }
        if (dto.getMovieId() != null) {
            review.setMovie(entityManager.getReference(Movie.class, dto.getMovieId()));
        }
    }
}
