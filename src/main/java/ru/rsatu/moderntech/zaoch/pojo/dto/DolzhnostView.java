package ru.rsatu.moderntech.zaoch.pojo.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
public class DolzhnostView {

    /**
     * Уникальный идетификатор должности
     */
    private Long id;

    /**
     * Название должности
     */
    private String name;

    /**
     * Оклад
     */
    private Double salary;


}
