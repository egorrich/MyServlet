package services;

import model.User;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by egor on 21.02.17.
 */
public class UserDAOHibernateImpl implements UserDAO{

    private Session session;

    @Override
    public void create(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println(user + " created");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }

    }

    @Override
    public void update(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            System.out.println(user + " id updated");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }

    }

    @Override
    public void delete(long id) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            User user = (User)session.load(User.class, id);
            session.delete(user);
            System.out.println("User with User ID = " + id + " is deleted");
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Override
    public List<User> findAll(int number) {
        return null;
    }

    @Override
    public List<User> findAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from User ");
            users = query.getResultList();

        } finally {
            HibernateUtil.close(session);
        }
        return users;
    }

    @Override
    public User findByName(String name) {
        User user = null;

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String queryString = "from User where name = :name";
            Query query = session.createQuery(queryString);
            query.setParameter("name", name);
            user = (User)query.getSingleResult();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
        return user;
    }

    @Override
    public User findById(long id) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User)session.get(User.class, id);
           // Hibernate.initialize(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
        return user;
    }

    /*private void close(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }*/
}
