package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.Optional;

public class MainClassWebServer {
    private static final Logger logger = LoggerFactory.getLogger(MainClassWebServer.class);
    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    private static final int WEB_SERVER_PORT = 8090;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        DBServiceUser dbServiceUser = createDBServiceUser();
//        dbServiceUser.saveUser(new User("Administrator", "Администратор", "asdasdassdsdsdds34534534fsd".hashCode(), 'y'));
//        dbServiceUser.saveUser(new User("Vasia", "Василий", "asdasdas".hashCode()));
//        dbServiceUser.saveUser(new User("Petia", "Пётр", "asdasdas".hashCode()));
//        dbServiceUser.saveUser(new User("Kolia", "Николай", "asdasdas".hashCode()));
//        Optional<User> administrator = dbServiceUser.getUser("Administrator");
//        logger.info("user = {}", administrator.orElse(null));
//        Optional<User> vasia = dbServiceUser.getUser("Vasia");
//        logger.info("user = {}", vasia.orElse(null));

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(dbServiceUser);

        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, dbServiceUser, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }

    public static DBServiceUser createDBServiceUser() {
        Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

//        String dbUrl = configuration.getProperty("hibernate.connection.url");
//        String dbUserName = configuration.getProperty("hibernate.connection.username");
//        String dbPassword = configuration.getProperty("hibernate.connection.password");
//
//        MigrationsExecutorFlyway flyway =
//                new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword);
//        flyway.cleanDb();
//        flyway.executeMigrations();

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(configuration, User.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);

        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DBServiceUserImpl(userDao);
        return dbServiceUser;
    }
}
