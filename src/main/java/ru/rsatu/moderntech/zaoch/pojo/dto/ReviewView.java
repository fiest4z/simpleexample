package ru.rsatu.moderntech.zaoch.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import ru.rsatu.moderntech.zaoch.pojo.entity.Movie;

@Getter
@Setter
public class ReviewView {
    private Long id;
    private String username;
    private String movieTitle;
    private Integer rating;
    private String comment;
}
