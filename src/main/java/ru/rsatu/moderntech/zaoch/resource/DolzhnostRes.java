package ru.rsatu.moderntech.zaoch.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import ru.rsatu.moderntech.zaoch.pojo.dto.DolzhnostView;
import ru.rsatu.moderntech.zaoch.pojo.dto.EmployeeSave;
import ru.rsatu.moderntech.zaoch.service.DolzhnostServ;
import jakarta.ws.rs.core.MediaType;  //для поддержки типов данных запроса


import java.util.List;

/**
 * API для работы с должностями
 */

@Path("/coursework/api/v1/dolzhnost")       //путь, по которому будут доступны методы (базовый)
public class DolzhnostRes {
    @Inject
    DolzhnostServ serv;

    /**
     * Получить список должностей
     * @return списко должностей
     */
    @GET
    @Path("loadDolzhnost")    //продолжение базового пути
    public List<DolzhnostView> load(){
        return serv.load();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createDolzhnost")
    public void create(DolzhnostView model){
        serv.save(model);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("updateDolzhnost")
    public void update(DolzhnostView model){
        serv.save(model);
    }

}
