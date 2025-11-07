package ru.rsatu.moderntech.zaoch.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;        //для поддержки типов данных запроса
import ru.rsatu.moderntech.zaoch.pojo.dto.DolzhnostView;
import ru.rsatu.moderntech.zaoch.pojo.dto.EmployeeSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.EmployeeView;
import ru.rsatu.moderntech.zaoch.service.DolzhnostServ;
import ru.rsatu.moderntech.zaoch.service.EmployeeServ;

import java.util.List;

/**
 * API для работы с сотрудниками
 */

@Path("/coursework/api/v1/employee")       //путь, по которому будут доступны методы (базовый)
public class EmployeeRes {
    @Inject
    EmployeeServ serv;

    /**
     * Получить список сотрдуников
     * @return списко дсотрдуников
     */
    @GET
    @Path("loadEmployee")    //продолжение базового пути
    public List<EmployeeView> load(){
        return serv.load();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createEmployee")
    public void create(EmployeeSave model){
        serv.save(model);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("updateEmployee")
    public void update(EmployeeSave model){
        serv.save(model);
    }

}
