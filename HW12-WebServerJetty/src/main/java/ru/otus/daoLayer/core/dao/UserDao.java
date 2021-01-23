package ru.otus.daoLayer.core.dao;

import ru.otus.businessLayer.model.User;
import ru.otus.daoLayer.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface UserDao {

    void insert(User user);

    void update(User user);

    Optional<User> findByName(String name);

    SessionManager getSessionManager();
}
