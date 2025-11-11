package ru.rsatu.moderntech.zaoch.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.rsatu.moderntech.zaoch.pojo.dto.MovieSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.MovieView;
import ru.rsatu.moderntech.zaoch.service.MovieServ;

import java.util.List;

@Path("/coursework/api/v1/movie")
public class MovieRes {

    @Inject
    MovieServ serv;


    @GET
    @Path("loadMovie")
    public List<MovieView> load() {
        return serv.load();
    }

    @GET
    @Path("getMovie/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieView getMovie(@PathParam("id") Long id) {
        return serv.findById(id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createMovie")
    public void create(MovieSave model) {
        serv.save(model);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("updateMovie")
    public void update(MovieSave model) {
        serv.update(model);
    }

    @DELETE
    @Path("deleteMovie/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            serv.delete(id);
            return Response.ok("Фильм удалён").build();

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
                    .entity("Ошибка при удалении фильма: " + e.getMessage())
                    .build();
        }
    }



}

