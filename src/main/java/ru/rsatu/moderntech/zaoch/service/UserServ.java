package ru.rsatu.moderntech.zaoch.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ru.rsatu.moderntech.zaoch.mapper.UserMapper;
import ru.rsatu.moderntech.zaoch.pojo.dto.UserSave;
import ru.rsatu.moderntech.zaoch.pojo.dto.UserView;
import ru.rsatu.moderntech.zaoch.pojo.entity.User;
import ru.rsatu.moderntech.zaoch.repository.UserRep;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServ {

    @Inject
    UserRep rep;

    @Inject
    UserMapper mapper;

    // Получение всех пользователей
    public List<UserView> load() {
        return rep.load().stream()
                .map(mapper::toUserView)  // используем метод маппера
                .collect(Collectors.toList());
    }

    // Создание или обновление пользователя
    @Transactional
    public void save(UserSave userSave) {

        // 1. Проверяем email
        User existingByEmail = rep.findByEmail(userSave.getUserEmail());

        // 2. Если создаём нового
        if (userSave.getId() == null) {
            if (existingByEmail != null) {
                throw new IllegalStateException("Пользователь с таким email уже существует");
            }
        }
        // 3. Если редактируем
        else {
            if (existingByEmail != null && !existingByEmail.getId().equals(userSave.getId())) {
                throw new IllegalStateException("Пользователь с таким email уже существует");
            }
        }

        User user = mapper.toUser(userSave);
        rep.save(user);
    }

    // Удаление пользователя
    @Transactional
    public void delete(Long id) {
        User user = rep.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("Пользователь не найден");
        }
        // проверка на связанные отзывы или watchlist (если нужно)
        // if (!user.getReviews().isEmpty()) throw new IllegalStateException("Нельзя удалить пользователя, который оставил отзывы");
        rep.delete(user.getId());
    }

    public UserView findById(Long id) {
        User user = rep.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("Пользователь не найден");
        }
        return mapper.toUserView(user);
    }


}




