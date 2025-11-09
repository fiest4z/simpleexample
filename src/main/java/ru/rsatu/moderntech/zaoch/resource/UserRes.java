package ru.rsatu.moderntech.zaoch.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ru.rsatu.moderntech.zaoch.pojo.dto.UserSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.UserView;
import ru.rsatu.moderntech.zaoch.service.UserServ;

import java.util.List;

@Path("/coursework/api/v1/user")
public class UserRes {

    @Inject
    UserServ serv;

    @GET
    @Path("loadUser")
    public List<UserView> load() {
        return serv.load();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createUser")
    public void create(UserSave model) {
        serv.save(model);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("updateUser")
    public void update(UserSave model) {
        serv.save(model);
    }
}
