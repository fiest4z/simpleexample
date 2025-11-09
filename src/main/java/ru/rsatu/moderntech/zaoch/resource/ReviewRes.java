package ru.rsatu.moderntech.zaoch.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ru.rsatu.moderntech.zaoch.pojo.dto.ReviewSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.ReviewView;
import ru.rsatu.moderntech.zaoch.service.ReviewServ;

import java.util.List;

@Path("/coursework/api/v1/review")
public class ReviewRes {

    @Inject
    ReviewServ serv;

    @GET
    @Path("loadReview")
    public List<ReviewView> load() {
        return serv.load();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createReview")
    public void create(ReviewSave model) {
        serv.save(model);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("updateReview")
    public void update(ReviewSave model) {
        serv.save(model);
    }

}
