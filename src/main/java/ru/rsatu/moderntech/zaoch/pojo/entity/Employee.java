package ru.rsatu.moderntech.zaoch.pojo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Comment;

@Getter     //автосоздание геттеров и сеттеров (из lombok)
@Setter
@Entity     //является отношением
@Table(name="employee") //имя таблицы
public class Employee {

    /**
     * Уникальный идетификатор сотрудника (первичный ключ)
     */
    @Getter
    @Setter
    @Id                                                                                 //уникальный первичный ключ
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "employee_id_gen")   //автогенерируемое значение, последовательное генерирование, указан генератор, чтобы различать с другими
    @SequenceGenerator(name = "employee_id_gen",
            sequenceName = "employee_id_gen_seq",
            initialValue = 10,              //стартовое значение и шаг стартов - для непересечения при работе нескольких копий
            allocationSize = 100)
    @Column(name = "id")                    //имя столбца (поля) в БД
    @Comment(value = "Уникальный идентификатор сотрудника (первичный ключ)")    //комментарий столбца
    private Long id;


    /**
     * ФИО сотрудника
     */
    @Column(name = "name")                    //имя столбца (поля) в БД
    @Comment(value = "ФИО сотрудника")    //комментарий столбца
    private String name;

    /**
     * Должность сотрудника
     */
    @ManyToOne(cascade=CascadeType.REMOVE)    //Один сотрдуник в одной должности
    @JoinColumn(name = "dolzhnost_id")      //название столбца в таблице (связанный столбец)
    @Comment(value = "Идентификатор должности из таблицы \"dolzhnost\"")    //комментарий
    private Dolzhnost dolzhnost;



}
