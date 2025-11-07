package ru.rsatu.moderntech.zaoch.pojo.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmployeeView {

    /**
     * Уникальный идетификатор сотрудника (первичный ключ)
     */
    private Long id;

    /**
     * ФИО сотрудника
     */
    private String name;

    /**
     * Должность сотрудника
     */
    private String dolzhnost;   //новые поля

    /**
     * Оклад сотрудника
     */
    private Double salary;           //новые поля





}
