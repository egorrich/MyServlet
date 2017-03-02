package by.myservlet.controller;

import by.myservlet.model.User;
import by.myservlet.services.UserDAO;
import by.myservlet.utils.SessionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Create on 28.02.17.
 *
 * @author egor
 */
@Controller
@RequestMapping(value = "/homeController")
public class HomeController {

    private final static String TABLE_NAME = "Users";

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
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
}
