package by.myservlet.controller;
import by.myservlet.utils.SessionValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

/**
 * Create on 28.02.17.
 *
 * @author egor
 */

@Controller
@RequestMapping(value = "/homeController")
public class HomeController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView printUsers(HttpSession session) {
        String sName = (String) session.getAttribute("sName");
        String sPassword = (String) session.getAttribute("sPassword");
        if (SessionValidator.validate(sName, sPassword)) {
            return new ModelAndView("home");
        } else {
            return new ModelAndView("login");
        }
    }
}
