package by.myservlet.controller;

import by.myservlet.model.User;
import by.myservlet.services.UserDAO;
import by.myservlet.utils.SessionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

/**
 * Create on 28.02.17.
 *
 * @author egor
 */
@Controller
@RequestMapping("/HomerController")
public class HomeController {

    private final static String TABLE_NAME = "Users";

    @Autowired
    private UserDAO userDAO;

//    protected ModelAndView handleRequestInternal(HttpServletRequest request,
//                                                 HttpServletResponse response) throws Exception {
//
//        ModelAndView model = new ModelAndView("WelcomePage");
//
//        return model;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/")
    public ModelAndView printUsers(@RequestParam("sName") String sName, @RequestParam("sPassword") String sPassword, Model model) {
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
