package ru.rsatu.moderntech.zaoch.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.moderntech.zaoch.pojo.dto.MovieSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.MovieView;
import ru.rsatu.moderntech.zaoch.pojo.entity.Movie;

@Mapper(componentModel = "jakarta")
public abstract class MovieMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "releaseYear", source = "releaseYear")
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "description", source = "description")
    public abstract MovieView toMovieView(Movie movie);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "releaseYear", source = "releaseYear")
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "description", source = "description")
    public abstract Movie toMovie(MovieSave dto);

    protected void updateMovieAfterMapping(@MappingTarget Movie movie, MovieSave dto) {
        if (dto.getTitle() != null) movie.setTitle(dto.getTitle());
        if (dto.getReleaseYear() != null) movie.setReleaseYear(dto.getReleaseYear());
        if (dto.getGenre() != null) movie.setGenre(dto.getGenre());
        if (dto.getDescription() != null) movie.setDescription((dto.getDescription()));
    }
}
