package ru.rsatu.moderntech.zaoch.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ru.rsatu.moderntech.zaoch.pojo.dto.ReviewSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.ReviewView;
import ru.rsatu.moderntech.zaoch.pojo.entity.Review;
import ru.rsatu.moderntech.zaoch.service.ReviewServ;

import java.util.List;

@Path("/coursework/api/v1/review")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewRes {

    @Inject
    ReviewServ serv;

    // Получение всех отзывов
    @GET
    public List<ReviewView> load() {
        return serv.load();
    }

    // Получение одного отзыва
    @GET
    @Path("{id}")
    public ReviewView getById(@PathParam("id") Long id) {
        return serv.findById(id);
    }

    // Создание нового отзыва
    @POST
    public void create(ReviewSave model) {
        serv.save(model);
    }

    // Обновление существующего отзыва
    @PUT
    public void update(ReviewSave model) {
        serv.save(model);
    }

    // Удаление отзыва
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        serv.delete(id);
    }
}


