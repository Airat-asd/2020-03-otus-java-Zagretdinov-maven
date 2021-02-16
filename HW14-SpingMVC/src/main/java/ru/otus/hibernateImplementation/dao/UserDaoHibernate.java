package ru.otus.hibernateImplementation.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.businessLayer.model.User;
import ru.otus.daoLayer.core.dao.DaoException;
import ru.otus.daoLayer.core.dao.UserDao;
import ru.otus.daoLayer.core.sessionmanager.SessionManager;
import ru.otus.hibernateImplementation.sessionmanager.DatabaseSessionHibernate;
import ru.otus.hibernateImplementation.sessionmanager.SessionManagerHibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoHibernate implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);
    private final SessionManagerHibernate sessionManager;
    private static final String MESSAGE_USER_ADDED = "Пользователь добавлен";
    private static final String MESSAGE_USER_NOT_ADDED = "Пользователь с таким логином уже существует!";
    private static final String EXCEPTION = "Добавить пользователя не удалось, обратитесь к администратору сайта.";

    public UserDaoHibernate(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, login));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            List<User> users = hibernateSession.createQuery("From User").list();
            return users;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
    public String insert(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        String message;
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.persist(user);
            hibernateSession.flush();
            message = MESSAGE_USER_ADDED;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if (e.getMessage().equals("org.hibernate.exception.ConstraintViolationException: could not execute statement")) {
                message = MESSAGE_USER_NOT_ADDED;
            } else message = EXCEPTION;
        }
        return message;
    }

    @Override
    public void update(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.merge(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
