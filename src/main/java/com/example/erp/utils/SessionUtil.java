package com.example.erp.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtil {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
//            String url = System.getenv("HIBERNATE_MYSQL_URL");
//            if (url != null) {
//                configuration.setProperty("hibernate.connection.url", "jdbc:mysql://"+url+"/platformdb?createDatabaseIfNotExist=true");
//                configuration.setProperty("hibernate.connection.username", "test");
//                configuration.setProperty("hibernate.connection.password", "test");
//            }
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
}