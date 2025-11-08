package ru.rsatu.moderntech.zaoch.mapper;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.moderntech.zaoch.pojo.dto.UserSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.UserView;
import ru.rsatu.moderntech.zaoch.pojo.entity.User;

@Mapper(componentModel = "jakarta")
public abstract class UserMapper {

    @Inject
    protected EntityManager entityManager;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "userEmail", source = "userEmail")
    public abstract UserView toUserView(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "userEmail", source = "userEmail")
    public abstract User toUser(UserSave userSave);

    @AfterMapping
    protected void updateUserAfterMapping(@MappingTarget User user, UserSave userSave) {
        if (userSave.getUsername() != null) user.setUsername(userSave.getUsername());
        if (userSave.getUserEmail() != null) user.setUserEmail(userSave.getUserEmail());
    }
}
