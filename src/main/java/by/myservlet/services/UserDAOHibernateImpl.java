package by.myservlet.services;

import by.myservlet.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

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
       // try {
            session = sessionFactory.getCurrentSession();
       /* } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }*/
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
        session = sessionFactory.getCurrentSession();
        /*try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }*/
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
        session = sessionFactory.getCurrentSession();
        /*try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }*/
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
    @Transactional
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User ");
        return (List<User>) query.getResultList();
    }

    @Override
    @Transactional
    public User findByName(String name) {
        User user = null;
        Session session;
  //      try {
            session = sessionFactory.getCurrentSession();
        /*} catch (HibernateException e) {
            session = sessionFactory.openSession();
        }*/
        try {
            String queryString = "from User where name = :name";
            Query query = session.createQuery(queryString);
            query.setParameter("name", name);
            user = (User) query.getSingleResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Transactional
    @Override
    public User findById(long id) {
        Session session;
        session = sessionFactory.getCurrentSession();
        try {
            return session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}