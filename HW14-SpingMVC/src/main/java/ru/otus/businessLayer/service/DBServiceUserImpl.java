package ru.otus.businessLayer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.businessLayer.model.User;
import ru.otus.daoLayer.core.dao.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DBServiceUserImpl implements DBServiceUser {
    private static final Logger logger = LoggerFactory.getLogger(DBServiceUserImpl.class);
    private static final String LOGIN_NOT_EMPTY = "Все записи должны быть заполнены!";

    private final UserDao userDao;

    public DBServiceUserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String saveUser(User user) {
        String message;
        if (!(user.getName().isEmpty())) {
            try (var sessionManager = userDao.getSessionManager()) {
                sessionManager.beginSession();
                try {
                    message = userDao.insert(user);
                    sessionManager.commitSession();

                    logger.info("save {}, id: {}", user.getClass().getSimpleName(), user.getLogin());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    sessionManager.rollbackSession();
                    throw new DbServiceException(e);
                }
            }
        } else {
            message = LOGIN_NOT_EMPTY;
        }
        return message;
    }

    @Override
    public Optional<User> getUser(String login) {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> userOptional = userDao.findByLogin(login);
                logger.info("client: {}", userOptional.orElse(null));
                return userOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                List<User> users = userDao.getAllUsers();
                logger.info("client: {}", users);
                return users;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return new ArrayList<>();
        }
    }
}
