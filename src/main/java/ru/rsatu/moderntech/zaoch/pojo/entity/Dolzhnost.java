package ru.rsatu.moderntech.zaoch.pojo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter    //автосоздание геттеров и сеттеров (из lombok)
@Setter
@Entity     //является отношением
@Table(name="dolzhnost") //имя таблицы
public class Dolzhnost {

    /**
     * Уникальный идетификатор должности
     */
    @Id                                                                                 //уникальный первичный ключ
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "dolzhnost_id_gen")   //автогенерируемое значение, последовательное генерирование, указан генератор, чтобы различать с другими
    @SequenceGenerator(name = "dolzhnost_id_gen",
            sequenceName = "dolzhnost_id_gen_seq",
            initialValue = 10,              //стартовое значение и шаг стартов - для непересечения при работе нескольких копий
            allocationSize = 100)
    @Column(name = "id")                    //имя столбца (поля) в БД
    @Comment(value = "Уникальный идентификатор должности")    //комментарий столбца
    private Long id;

    /**
     * Название должности
     */
    @Column(name = "name")                    //имя столбца (поля) в БД
    @Comment(value = "Название должности")    //комментарий столбца
    private String name;

    /**
     * Оклад
     */
    @Column(name = "salary")                    //имя столбца (поля) в БД
    @Comment(value = "Оклад")    //комментарий столбца
    private Double salary;

}
