package ru.rsatu.moderntech.zaoch.pojo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WatchlistView {

    private Long id;
    private String username;
    private String movieTitle;
    private String status;
}
