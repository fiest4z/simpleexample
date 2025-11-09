package ru.rsatu.moderntech.zaoch.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ru.rsatu.moderntech.zaoch.mapper.UserMapper;
import ru.rsatu.moderntech.zaoch.pojo.dto.UserSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.UserView;
import ru.rsatu.moderntech.zaoch.repository.UserRep;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServ {
    @Inject
    UserMapper mapper;

    @Inject
    UserRep rep;


    public List<UserView> load() {
        return rep.load().stream().map(mapper::toUserView).collect(Collectors.toList());
    }

    public void save(UserSave model) {
        rep.save(mapper.toUser(model));
    }
}


