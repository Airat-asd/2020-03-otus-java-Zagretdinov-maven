package ru.otus.daoLayer.core.dao;

import ru.otus.businessLayer.model.User;
import ru.otus.daoLayer.core.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    String insert(User user);

    void update(User user);

    Optional<User> findByLogin(String login);

    SessionManager getSessionManager();

    Optional<List<User>> getAllUsers();
}
