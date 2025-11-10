package ru.rsatu.moderntech.zaoch.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ru.rsatu.moderntech.zaoch.mapper.WatchlistMapper;
import ru.rsatu.moderntech.zaoch.pojo.dto.WatchlistSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.WatchlistView;
import ru.rsatu.moderntech.zaoch.pojo.entity.Watchlist;
import ru.rsatu.moderntech.zaoch.repository.WatchlistRep;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class WatchlistServ {

    @Inject
    WatchlistMapper mapper;

    @Inject
    WatchlistRep rep;

    public List<WatchlistView> load() {
        return rep.load().stream().map(mapper::toWatchlistView).collect(Collectors.toList());
    }

    @Transactional
    public void save(WatchlistSave model) {
        Watchlist dbModel = mapper.toWatchlist(model);

        // проверка на дубликат
        boolean exists = rep.existsByUserAndMovie(dbModel.getUser().getId(),
                dbModel.getMovie().getId(),
                dbModel.getId());

        if (exists) {
            throw new RuntimeException("Этот пользователь уже добавил этот фильм в watchlist");
        }

        rep.save(dbModel);
    }

    @Transactional
    public void delete(Long id) {
        rep.delete(id);
    }

    public WatchlistView findById(Long id) {
        return mapper.toWatchlistView(rep.findById(id));
    }
}

