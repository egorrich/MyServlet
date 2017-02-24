package by.myservlet;

import by.myservlet.model.User;
import by.myservlet.services.UserDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create on 13.2.17.

 * @author egor
 */
public class Main {

    User user;

    UserDAO userDAO;

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/spring.xml");

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
