import model.User;
import services.UserDAO;
import services.UserDAOHibernateImpl;

import java.util.List;

/**
 * Create on 13.2.17.

 * @author egor
 */
public class Main {
    public static void main(String[] args) {
       UserDAO userDAO = new UserDAOHibernateImpl();
        List<User> users = userDAO.findAll();
        System.out.println(users);
    }
}
