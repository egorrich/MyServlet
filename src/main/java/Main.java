import model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.UserDAO;

/**
 * Create on 13.2.17.

 * @author egor
 */
public class Main {

    User user;

    UserDAO userDAO;

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        Main main = (Main) context.getBean("Main");

        main.userDAO.create((User) context.getBean("user"));
        //userDAO.create(user);

    }

    public Main() {
    }

    public Main(User user, UserDAO userDAO) {
        this.user = user;
        this.userDAO = userDAO;
    }
}
