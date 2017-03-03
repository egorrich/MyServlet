package by.myservlet.controller;
import by.myservlet.model.User;
import by.myservlet.services.UserDAO;
import by.myservlet.utils.SessionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Create on 02.03.17.
 *
 * @author egor
 */

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final static String TABLE_NAME = "Users";

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printUsers(HttpSession session, Model model) {
        String sName = (String) session.getAttribute("sName");
        String sPassword = (String) session.getAttribute("sPassword");
        if (SessionValidator.validate(sName, sPassword)) {
            List<User> list = userDAO.findAll();
            Collections.sort(list);
            model.addAttribute("list", list);
            model.addAttribute("TableName", TABLE_NAME);
            return new ModelAndView("home");
        } else {
            return new ModelAndView("login");
        }
    }

    @RequestMapping(value = "/user/{id}", produces = "text/html;charset=UTF-8", method = RequestMethod.DELETE)
    public String doDelete(@PathVariable long id) {
        userDAO.delete(id);
        return "home";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String doUpdate(HttpSession session,
                           @RequestParam(value = "operation", required = false) String operation,
                           @RequestParam(value = "id", required = false) Long id,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "lastName", required = false) String lastname,
                           @RequestParam(value = "password", required = false) String password) {
        if (operation == null || operation.equals("update")) {
            if(operation == null) {
                User user = new User(id, name, lastname, password);
                session.setAttribute("user", user);
                return "users";
            } else {
                String persistedUserName = getPersistedUserName(id);
                User user = new User(id, name, lastname, password);
                userDAO.update(user);
                session.setAttribute("user", null);
                String sName = (String) session.getAttribute("sName");
                if (persistedUserName.equals(sName)) {
                    session.setAttribute("sName", name);
                }
                return "home";
            }
        }
        if (!(operation == null) && operation.equals("delete")) {
            userDAO.delete(id);
            return "home";
        }
        return null;
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String doCreate(@RequestParam(value = "operation", required = false) String operation,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "lastName", required = false) String lastname,
                           @RequestParam(value = "password", required = false) String password) {
        if(operation == null) {
            logger.info("doCreate is working. Redirecting to users.jsp");
            return "users";
        }
        if (operation.equals("create")) {
            userDAO.create(new User(name, lastname, password));
            logger.info("User created");
        }
        return "home";
    }

    private String getPersistedUserName(long id) {
        User persistedUser = userDAO.findById(id);
        return persistedUser.getName();
    }
}

