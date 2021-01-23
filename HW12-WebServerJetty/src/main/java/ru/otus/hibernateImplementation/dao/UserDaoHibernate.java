package ru.otus.hibernateImplementation.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.businessLayer.model.User;
import ru.otus.daoLayer.core.dao.DaoException;
import ru.otus.daoLayer.core.dao.UserDao;
import ru.otus.daoLayer.core.sessionmanager.SessionManager;
import ru.otus.hibernateImplementation.sessionmanager.DatabaseSessionHibernate;
import ru.otus.hibernateImplementation.sessionmanager.SessionManagerHibernate;

import java.util.Optional;

public class UserDaoHibernate implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);
    private final SessionManagerHibernate sessionManager;

    public UserDaoHibernate(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> findByName(String name) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, name));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public void insert(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            if ( ! (user.getName().isEmpty())) {
                Session hibernateSession = currentSession.getHibernateSession();
                hibernateSession.persist(user);
                hibernateSession.flush();
            } else {
                throw new DaoException(new Exception("Имя не может быть пустым."));
            }
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.merge(user);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

//    @Override
//    public void insertOrUpdate(User user) {
//        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
//        try {
//            Session hibernateSession = currentSession.getHibernateSession();
//            if (! (user.getName().isEmpty())) {
//                hibernateSession.merge(user);
//            } else {
//                hibernateSession.persist(user);
//                hibernateSession.flush();
//            }
//        } catch (Exception e) {
//            throw new DaoException(e);
//        }
//    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
