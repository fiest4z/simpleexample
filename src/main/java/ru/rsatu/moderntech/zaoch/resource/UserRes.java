package ru.rsatu.moderntech.zaoch.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    @Produces(MediaType.APPLICATION_JSON)
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

    @DELETE
    @Path("deleteUser/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            serv.delete(id);
            return Response.ok("Пользователь удалён").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("Ошибка при удалении пользователя: " + e.getMessage())
                    .build();
        }
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserView getById(@PathParam("id") Long id) {
        return serv.findById(id); // метод findById в UserServ
    }



}
