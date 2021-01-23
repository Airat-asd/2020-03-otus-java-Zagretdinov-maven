package ru.otus.businessLayer.service;

import ru.otus.businessLayer.model.User;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    void saveUser(User user);

    Optional<User> getUser(String name);

    Optional<List<User>> getAllUsers();
}
