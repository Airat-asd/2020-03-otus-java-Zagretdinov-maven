package ru.otus.businessLayer.service;

import ru.otus.businessLayer.dto.Dto;
import ru.otus.businessLayer.model.User;

public class MappingUserToDto {

    public static Dto mapToDtoUser(User user) {
        Dto dtoUser = new Dto();
        dtoUser.setName(user.getName());
        dtoUser.setLogin(user.getLogin());
        return dtoUser;
    }

    public static User mapToUser(Dto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setPasswordHash(dto.getPassword().hashCode());
        return user;
    }
}
