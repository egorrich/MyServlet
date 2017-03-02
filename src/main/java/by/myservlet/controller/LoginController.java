package by.myservlet.controller;

import by.myservlet.model.User;
import by.myservlet.services.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Create on 01.03.17.
 *
 * @author egor
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class.getName());

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doLogin(HttpSession session, @RequestParam("name") String name, @RequestParam("password") String password) {
        User user = userDAO.findByName(name);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                log.info("User logged in: " + user);
                session.setAttribute("sName", name);
                session.setAttribute("sPassword", password);
                session.setAttribute("list", null);
                return "home";
            }
        }
        return "login";
    }
}
