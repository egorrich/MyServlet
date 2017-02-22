import services.UserDAO;
import services.UserDAOImpl;

/**
 * Create on 13.2.17.

 * @author egor
 */
public class Main {
    public static void main(String[] args) {
       UserDAO userDAO = new UserDAOImpl();
      // userDAO.create(new User("esdf", "fsd", "sf2"));
        userDAO.create(null);
        userDAO.delete(77);

    }
}
