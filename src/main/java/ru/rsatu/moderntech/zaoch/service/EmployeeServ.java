package ru.rsatu.moderntech.zaoch.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ru.rsatu.moderntech.zaoch.mapper.EmployeeMapper;
import ru.rsatu.moderntech.zaoch.pojo.dto.DolzhnostView;
import ru.rsatu.moderntech.zaoch.pojo.dto.EmployeeSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.EmployeeView;
import ru.rsatu.moderntech.zaoch.pojo.entity.Employee;
import ru.rsatu.moderntech.zaoch.repository.DolzhnostRep;
import ru.rsatu.moderntech.zaoch.repository.EmployeeRep;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeServ {
    @Inject
    EmployeeMapper mapper;

    @Inject
    EmployeeRep rep;


    /**
     * Получение списка должностей
     * @return список должностей
     */
    public List<EmployeeView> load(){

        //хагрузка данных, разделение на составляющие списка, маппинг в представление и сборка в список
        return rep.load().stream()
                .map(mapper::toEmployeeView)
                .collect(Collectors.toList());
    }

    /**
     * Сохранение должности
     * @param model
     */
    public void save(EmployeeSave model){
        rep.save(mapper.toEmployee(model));    //отдача мапперу и сохранение результата
    }

}
