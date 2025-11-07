package ru.rsatu.moderntech.zaoch.mapper;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.moderntech.zaoch.pojo.dto.EmployeeSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.EmployeeView;
import ru.rsatu.moderntech.zaoch.pojo.entity.Dolzhnost;
import ru.rsatu.moderntech.zaoch.pojo.entity.Employee;

@Mapper(componentModel = "jakarta") //позволяет извлекать маппер в нужное место
public abstract class EmployeeMapper {

    @Inject
    EntityManager entityManager; //мeнеждер работы с БД

    /**
     * Преобразовать в модель представления
     * @param from модель из БД
     * @return модель представления
     */
    @Mapping(target = "id",source = "id")   //что во что маппится
    @Mapping(target = "name",source = "name")
    @Mapping(target = "dolzhnost",source = "dolzhnost.name")    //разделение объекта должность на составляющие
    @Mapping(target = "salary",source = "dolzhnost.salary")
    public abstract EmployeeView toEmployeeView(Employee from);

    /**
     * Преобразовать в модель БД
     * @param from модель представления
     * @return модель БД
     */
    @Mapping(target = "id",source = "id")   //что во что маппится
    @Mapping(target = "name",source = "name")
    public abstract Employee toEmployee(EmployeeSave from);


    //после маппинга добавляется вложенный объект
    @AfterMapping
    protected void updateEmployeeAfterMapping(@MappingTarget Employee db_model,EmployeeSave from){  //добавление сложенной сущность по ИД
        db_model.setDolzhnost(entityManager.getReference(Dolzhnost.class,from.getDolzhnostId()));
    }

}
