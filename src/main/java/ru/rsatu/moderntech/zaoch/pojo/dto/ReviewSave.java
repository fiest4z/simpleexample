package ru.rsatu.moderntech.zaoch.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.ast.tree.expression.Star;
import ru.rsatu.moderntech.zaoch.pojo.entity.Movie;
import ru.rsatu.moderntech.zaoch.pojo.entity.User;


@Getter
@Setter
public class ReviewSave {

    private Long id;

    private Long userId;

    private Long movieId;

    private Integer rating;

    private String comment;

}
