package ru.rsatu.moderntech.zaoch.pojo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;


@Getter
@Setter
@Entity
@Table(name = "reviews")

public class Review {


    //уникальный идентификатор обзора
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "review_id_gen")
    @SequenceGenerator(name = "review_id_gen",
            sequenceName = "review_id_gen_seq",
            initialValue = 10,
            allocationSize = 100)
    @Column(name = "id")
    @Comment(value = "Уникальный идентификатор обзора")
    private Long id;

    //идентификатор пользователя

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Comment(value = "Идентификатор пользователя из таблицы User")
    private User user;

    //идентификатор фильма
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @Comment(value = "Идентификатор фильма из таблицы Movie")
    private Movie movie;

    //рейтинг
    @Column(name = "rating")
    @Comment(value = "Рейтинг")
    private Integer rating;

    //комментарий

    @Column(name = "comment")
    @Comment(value = "Комментарий")
    private String comment;

}
