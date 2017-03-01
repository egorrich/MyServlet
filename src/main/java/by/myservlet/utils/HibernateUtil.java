package by.myservlet.utils;


import by.myservlet.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

    static private Logger log = LoggerFactory.getLogger(HibernateUtil.class.getName());

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
        } catch (Throwable ex) {
            log.error("Initial SessionFactory creation failed: ", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}

