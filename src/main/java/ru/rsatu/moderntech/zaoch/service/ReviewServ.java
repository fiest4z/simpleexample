package ru.rsatu.moderntech.zaoch.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ru.rsatu.moderntech.zaoch.mapper.ReviewMapper;
import ru.rsatu.moderntech.zaoch.pojo.dto.ReviewSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.ReviewView;
import ru.rsatu.moderntech.zaoch.pojo.entity.Review;
import ru.rsatu.moderntech.zaoch.repository.ReviewRep;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReviewServ {

    @Inject
    ReviewMapper mapper;

    @Inject
    ReviewRep rep;

    public List<ReviewView> load() {
        return rep.load().stream().map(mapper::toReviewView).collect(Collectors.toList());
    }

    public void save(ReviewSave model) {
        rep.save(mapper.toReview(model));
    }

    @Transactional
    public void delete(Long id) {
        rep.delete(id);
    }
    public ReviewView findById(Long id) {
        Review review = rep.findById(id);
        return mapper.toReviewView(review);
    }

}
