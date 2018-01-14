package org.simonscode.photoshare;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure("hibernate.cgf.xml").buildSessionFactory();
        } catch (Exception e) {
            // Make sure you log the exception, as it might be swallowed
            System.out.println("Initial SessionFactory creation failed.");
            e.printStackTrace();
            System.out.println();
            throw new ExceptionInInitializerError(e);
        }
    }
    public static void shutdown() {
        sessionFactory.close();
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
