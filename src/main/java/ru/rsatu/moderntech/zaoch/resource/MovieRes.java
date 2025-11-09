package ru.rsatu.moderntech.zaoch.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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
        serv.save(model);
    }

}
