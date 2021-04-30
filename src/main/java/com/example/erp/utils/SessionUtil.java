package com.example.erp.utils;

import com.example.erp.bean.Application;
import com.example.erp.bean.Login;
import com.example.erp.bean.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class SessionUtil {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            String url = System.getenv("HIBERNATE_MYSQL_URL");
            if (url != null) {
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://"+url+"/platformdb?createDatabaseIfNotExist=true");
                settings.put(Environment.USER, "test");
                settings.put(Environment.PASS, "test");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Login.class);
                configuration.addAnnotatedClass(Resource.class);
                configuration.addAnnotatedClass(Application.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
                ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);

            }else {
                configuration.configure();
                ourSessionFactory = configuration.buildSessionFactory();
            }
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
}