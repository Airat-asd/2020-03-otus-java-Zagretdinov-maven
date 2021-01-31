package ru.otus;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.otus.businessLayer.model.User;
import ru.otus.businessLayer.service.DBServiceUser;
import ru.otus.businessLayer.service.DBServiceUserImpl;
import ru.otus.daoLayer.core.dao.UserDao;
import ru.otus.hibernateImplementation.HibernateUtils;
import ru.otus.hibernateImplementation.dao.UserDaoHibernate;
import ru.otus.hibernateImplementation.sessionmanager.SessionManagerHibernate;
import ru.otus.server.UsersWebServer;
import ru.otus.server.UsersWebServerWithFilterBasedSecurity;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.TemplateProcessorImpl;
import ru.otus.services.UserAuthService;
import ru.otus.services.UserAuthServiceImpl;


public class MainClassWebServer {
    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    private static final int WEB_SERVER_PORT = 8090;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        DBServiceUser dbServiceUser = createDBServiceUser();

        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(dbServiceUser);

        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, dbServiceUser, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }

    public static DBServiceUser createDBServiceUser() {
        Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(configuration, User.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);

        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DBServiceUserImpl(userDao);
        return dbServiceUser;
    }
}
