package ru.rsatu.moderntech.zaoch.pojo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSave {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String genre;
    private String description;
}
