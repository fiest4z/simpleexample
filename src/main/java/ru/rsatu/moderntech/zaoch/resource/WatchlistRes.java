package ru.rsatu.moderntech.zaoch.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.rsatu.moderntech.zaoch.pojo.dto.WatchlistSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.WatchlistView;
import ru.rsatu.moderntech.zaoch.service.WatchlistServ;

import java.util.List;

@Path("/coursework/api/v1/watchlist")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WatchlistRes {

    @Inject
    WatchlistServ serv;

    @GET
    @Path("loadWatchlist")
    public List<WatchlistView> load() {
        return serv.load();
    }

    @GET
    @Path("{id}")
    public WatchlistView getById(@PathParam("id") Long id) {
        return serv.findById(id);
    }

    @POST
    @Path("createWatchlist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(WatchlistSave model) {
        try {
            serv.save(model);
            return Response.ok().build();
        } catch (RuntimeException e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }


    @PUT
    @Path("updateWatchlist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(WatchlistSave model) {
        try {
            serv.save(model);
            return Response.ok().build();
        } catch (RuntimeException e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("deleteWatchlist/{id}")
    public void delete(@PathParam("id") Long id) {
        serv.delete(id);
    }


}

