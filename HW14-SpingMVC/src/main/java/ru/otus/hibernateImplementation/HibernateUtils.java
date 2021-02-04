package ru.otus.hibernateImplementation;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;


import java.util.Arrays;

@org.springframework.context.annotation.Configuration
public class HibernateUtils {

    @Bean
    public static SessionFactory buildSessionFactory(String hibernateConfigFile, Class<?>... annotatedClasses) {
        Configuration configuration = new Configuration().configure(hibernateConfigFile);
        return buildSessionFactory(configuration, annotatedClasses);
    }

    @Bean
    public SessionFactory buildSessionFactory(Configuration configuration) {
        MetadataSources metadataSources = new MetadataSources(createServiceRegistry(configuration));

        Arrays.stream(annotatedClasses).forEach(metadataSources::User.class);

        Metadata metadata = metadataSources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    private StandardServiceRegistry createServiceRegistry(Configuration configuration) {
        return new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
    }

    @Bean
    public Configuration configuration() {
        Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);
        configuration.setProperty("hibernate.connection.url", dbUrl);
        configuration.setProperty("hibernate.connection.username", dbUserName);
        configuration.setProperty("hibernate.connection.password", dbPassword);
        return configuration;
    }
}
