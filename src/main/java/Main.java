import services.UserDAO;
import services.UserDAOImpl;

/**
 * Created by egor on 13.2.17.
 */
public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
        System.out.println(userDAO.findByName("Egor"));
       // userDAO.delete(3);
       // userDAO.create(new User("Egor", "Ivanov"));

    }
}
