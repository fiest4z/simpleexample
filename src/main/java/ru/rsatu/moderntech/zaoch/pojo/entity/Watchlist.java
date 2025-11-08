package ru.rsatu.moderntech.zaoch.pojo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Table(name = "watchlists")

public class Watchlist {

    @Getter
    @Setter
    @Id                                                                                 //уникальный первичный ключ
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "watchlist_id_gen")
    @SequenceGenerator(name = "watchlist_id_gen",
            sequenceName = "watchlist_id_gen_seq",
            initialValue = 10,
            allocationSize = 100)
    @Column(name = "id")
    @Comment(value = "Уникальный идентификатор списка")
    private Long id;

    //пользователь
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Comment(value = "Пользователь, которому принадлежит запись в watchlist")
    private User user;

    //фильм
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @Comment(value = "Фильм в watchlist")
    private Movie movie;

    //статус
    @Column(name = "status")
    @Comment(value = "Статус")
    private String status;
}
