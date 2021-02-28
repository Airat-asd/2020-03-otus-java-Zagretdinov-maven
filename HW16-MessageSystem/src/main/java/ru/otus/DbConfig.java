package ru.otus;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.businessLayer.model.User;
import ru.otus.businessLayer.service.DBServiceUser;
import ru.otus.businessLayer.service.Subscriber;
import ru.otus.controllers.UserController;
import ru.otus.db.handlers.GetListOfUserDataRequestHandler;
import ru.otus.db.handlers.GetUserDataRequestHandler;
import ru.otus.db.handlers.SaveUserDataRequestHandler;
import ru.otus.front.FrontendService;
import ru.otus.front.FrontendServiceImpl;
import ru.otus.front.handlers.GetUserDataResponseHandler;
import ru.otus.hibernateImplementation.HibernateUtils;
import ru.otus.messagesystem.HandlersStore;
import ru.otus.messagesystem.HandlersStoreImpl;
import ru.otus.messagesystem.MessageSystem;
import ru.otus.messagesystem.client.CallbackRegistry;
import ru.otus.messagesystem.client.MsClient;
import ru.otus.messagesystem.client.MsClientImpl;
import ru.otus.messagesystem.message.MessageType;

@Configuration
@ComponentScan
public class DbConfig {
    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";
    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";
    private static final Class<?> clazz = User.class;

    @Bean
    public FrontendService frontendService(DBServiceUser dbServiceUser1, DBServiceUser dbServiceUser2,
                                           DBServiceUser dbServiceUser3, MessageSystem messageSystem,
                                           CallbackRegistry callbackRegistry, Subscriber subscriber) {
        HandlersStore requestHandlerDatabaseStore = new HandlersStoreImpl();
        HandlersStore requestHandlerFrontendStore = new HandlersStoreImpl();

        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerFrontendStore, callbackRegistry);
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerDatabaseStore, callbackRegistry);

        FrontendService frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME, subscriber);
        requestHandlerDatabaseStore.addHandler(MessageType.USER_DATA, new GetUserDataRequestHandler(dbServiceUser1));
        requestHandlerDatabaseStore.addHandler(MessageType.LIST_USER_DATA, new GetListOfUserDataRequestHandler(dbServiceUser2));
        requestHandlerDatabaseStore.addHandler(MessageType.SAVE_USER_DATA, new SaveUserDataRequestHandler(dbServiceUser3));

        requestHandlerFrontendStore.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(callbackRegistry));
        requestHandlerFrontendStore.addHandler(MessageType.LIST_USER_DATA, new GetUserDataResponseHandler(callbackRegistry));
        requestHandlerFrontendStore.addHandler(MessageType.SAVE_USER_DATA, new GetUserDataResponseHandler(callbackRegistry));

        messageSystem.addClient(frontendMsClient);
        messageSystem.addClient(databaseMsClient);
        return frontendService;
    }

    @Bean
    public org.hibernate.cfg.Configuration configuration() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure(HIBERNATE_CFG_FILE);
        return configuration;
    }

    @Bean
    public SessionFactory sessionFactory(org.hibernate.cfg.Configuration configuration) {
        return HibernateUtils.buildSessionFactory(configuration, clazz);
    }

    @Bean
    public Session session(SessionFactory sessionFactory) {
        return sessionFactory.openSession();
    }
}