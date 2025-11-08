package ru.rsatu.moderntech.zaoch.pojo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WatchlistSave {

    private Long userId;

    private Long movieId;

    private String status;
}
