package by.myservlet.services;

import by.myservlet.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Create on 21.02.17.
 *
 * @author egor
 */
@Repository

public class UserDAOHibernateImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(User user) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println(user + " created");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(User user) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        try {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            System.out.println(user + " id updated");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(long id) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        try {
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            System.out.println("User with User ID = " + id + " is deleted");
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> findAll() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        List<User> users;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from User ");
            users = query.getResultList();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public User findByName(String name) {
        User user = null;
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        try {
            session.beginTransaction();
            String queryString = "from User where name = :name";
            Query query = session.createQuery(queryString);
            query.setParameter("name", name);
            user = (User) query.getSingleResult();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public User findById(long id) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        try {
            return session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}