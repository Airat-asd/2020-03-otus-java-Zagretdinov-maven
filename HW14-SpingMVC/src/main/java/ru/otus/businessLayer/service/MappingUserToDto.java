package ru.otus.businessLayer.service;

import org.springframework.stereotype.Service;
import ru.otus.businessLayer.dto.DtoUser;
import ru.otus.businessLayer.model.User;

@Service
public class MappingUserToDto {

    public static DtoUser mapToDtoUser(User user) {
        DtoUser dtoUser = new DtoUser();
        dtoUser.setName(user.getName());
        dtoUser.setLogin(user.getLogin());
        return dtoUser;
    }

    public static User mapToUser(DtoUser dtoUser) {
        User user = new User();
        user.setName(dtoUser.getName());
        user.setLogin(dtoUser.getLogin());
        user.setPasswordHash(dtoUser.getPassword().hashCode());
        return user;
    }
}
