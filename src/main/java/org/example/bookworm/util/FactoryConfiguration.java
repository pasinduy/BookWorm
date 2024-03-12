package org.example.bookworm.util;

import org.example.bookworm.entity.Admin;
import org.example.bookworm.entity.Book;
import org.example.bookworm.entity.Branch;
import org.example.bookworm.entity.Reader;
import org.example.bookworm.entity.Reservation;
import org.example.bookworm.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration(){
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        standardServiceRegistryBuilder.loadProperties("hibernate.properties");
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistryBuilder.build());
        metadataSources.
                addAnnotatedClass(Admin.class).
                addAnnotatedClass(Book.class).
                addAnnotatedClass(Branch.class).
                addAnnotatedClass(Borrow.class).
                addAnnotatedClass(User.class);
        Metadata metadata = metadataSources.getMetadataBuilder().build();
        sessionFactory =  metadata.getSessionFactoryBuilder().build();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
