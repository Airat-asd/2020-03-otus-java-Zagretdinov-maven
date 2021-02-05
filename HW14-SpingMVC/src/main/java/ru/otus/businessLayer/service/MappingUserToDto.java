package ru.otus.businessLayer.service;

import org.springframework.stereotype.Service;
import ru.otus.businessLayer.dto.SaveUser;
import ru.otus.businessLayer.model.User;

@Service
public class MappingUserToDto {

    public SaveUser mapToSaveUser(User user) {
        SaveUser saveUser = new SaveUser();
        saveUser.setName(user.getName());
        saveUser.setLogin(user.getLogin());
        return saveUser;
    }

    public User mapToUser(SaveUser saveUser) {
        User user = new User();
        user.setName(saveUser.getName());
        user.setLogin(saveUser.getName());
        user.setPasswordHash(saveUser.getPassword().hashCode());
        return user;
    }
}
