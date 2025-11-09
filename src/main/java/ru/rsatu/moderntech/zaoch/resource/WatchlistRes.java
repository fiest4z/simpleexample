package ru.rsatu.moderntech.zaoch.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ru.rsatu.moderntech.zaoch.pojo.dto.WatchlistSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.WatchlistView;
import ru.rsatu.moderntech.zaoch.service.WatchlistServ;

import java.util.List;

@Path("/coursework/api/v1/watchlist")
public class WatchlistRes {

    @Inject
    WatchlistServ serv;

    @GET
    @Path("loadWatchlist")
    public List<WatchlistView> load() {
        return serv.load();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createWatchlist")
    public void create(WatchlistSave model) {
        serv.save(model);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("updateWatchlist")
    public void update(WatchlistSave model) {
        serv.save(model);
    }


    @DELETE
    @Path("deleteWatchlist/{id}")
    public void delete(@PathParam("id") Long id) {
        serv.delete(id);
    }
}
