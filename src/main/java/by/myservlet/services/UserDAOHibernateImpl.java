package by.myservlet.services;

import by.myservlet.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserDAOHibernateImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void create(User user) {
        Session session;
        session = sessionFactory.getCurrentSession();
        try {
            session.save(user);
            logger.info(user + " created");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void update(User user) {
        Session session;
        session = sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(user);
            logger.info(user + " id updated");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session;
        session = sessionFactory.getCurrentSession();
        try {
            User user = session.load(User.class, id);
            session.delete(user);
            logger.info("User with User ID = " + id + " is deleted");
        } catch (RuntimeException e) {
            e.printStackTrace();
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
            session = sessionFactory.getCurrentSession();
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


    @Override
    @Transactional
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