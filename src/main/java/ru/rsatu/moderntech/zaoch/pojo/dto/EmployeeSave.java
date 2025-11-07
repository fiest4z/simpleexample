package ru.rsatu.moderntech.zaoch.pojo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeSave {

    /*
            * Уникальный идетификатор сотрудника (первичный ключ)
     */
    private Long id;

    /**
     * ФИО сотрудника
     */
    private String name;

    /**
     * Должность сотрудника (id)
     */
    private Long dolzhnostId;   //новые поля


}
