package ru.rsatu.moderntech.zaoch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rsatu.moderntech.zaoch.pojo.dto.DolzhnostView;
import ru.rsatu.moderntech.zaoch.pojo.entity.Dolzhnost;


@Mapper(componentModel = "jakarta") //позволяет извлекать маппер в нужное место
public abstract class DolzhnostMapper {

    /**
     * Преобразовать в модель представления
     * @param from модель из БД
     * @return модель представления
     */
    @Mapping(target = "id",source = "id")   //что во что маппится
    @Mapping(target = "name",source = "name")
    @Mapping(target = "salary",source = "salary")
    public abstract DolzhnostView toDolzhnostView(Dolzhnost from);

    /**
     * Преобразовать в модель БД
     * @param from модель представления
     * @return модель БД
     */
    @Mapping(target = "id",source = "id")   //что во что маппится
    @Mapping(target = "name",source = "name")
    @Mapping(target = "salary",source = "salary")
    public abstract Dolzhnost toDolzhnost(DolzhnostView from);
}
