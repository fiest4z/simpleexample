package ru.rsatu.moderntech.zaoch.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ru.rsatu.moderntech.zaoch.mapper.WatchlistMapper;
import ru.rsatu.moderntech.zaoch.pojo.dto.WatchlistSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.WatchlistView;
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

    public void save(WatchlistSave model) {
        rep.save(mapper.toWatchlist(model));
    }

    @Transactional
    public void delete(Long id) {
        rep.delete(id);
    }
}
