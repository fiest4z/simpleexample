package ru.rsatu.moderntech.zaoch.pojo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Table(name = "movies")

public class Movie {

    @Getter
    @Setter
    @Id             //уникальный первичный ключ
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "movie_id_gen")
    @SequenceGenerator(name = "movie_id_gen",
            sequenceName = "movie_id_gen_seq",
            initialValue = 10,
            allocationSize = 100)
    @Column(name = "id")
    @Comment(value = "Уникальный идентификатор фильма")    //комментарий столбца
    private Long id;

    //Название фильма
    @Column(name = "title", nullable = false)
    @Comment(value = "Название")
    private String title;

    //Год выпуска
    @Column(name = "release_year")
    @Comment(value = "Год выпуска")
    private Integer releaseYear;

    //Жанр
    @Column(name = "genre")
    @Comment(value = "Жанр")
    private String genre;

    //Описание
    @Column(name = "description")
    @Comment(value = "Описание")
    private String description;
}
