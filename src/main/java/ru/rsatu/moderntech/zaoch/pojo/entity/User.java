package ru.rsatu.moderntech.zaoch.pojo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users") // Имя таблицы
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_id_gen")
    @SequenceGenerator(name = "user_id_gen",
            sequenceName = "user_id_gen_seq",
            initialValue = 10,
            allocationSize = 100)
    @Column(name = "id")
    @Comment(value = "Уникальный идентификатор пользователя")
    private Long id;

    //имя пользователя
    @Column(name = "username", nullable = false, unique = true)
    @Comment(value = "Имя пользователя")
    private String username;

    //электронная почта
    @Column(name = "user_email", nullable = false, unique = true)
    @Comment(value = "Электронная почта")
    private String userEmail;

    //watchlist пользователя
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Watchlist> watchlist;
}
