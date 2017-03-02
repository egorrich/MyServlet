package by.myservlet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

/**
 * Create on 02.03.17.
 *
 * @author egor
 */
@Controller
@RequestMapping(value = "/logout")
public class LogoutController {

    private static final Logger logger = LoggerFactory.getLogger(LogoutController.class.getName());

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    public String doLogout(HttpSession session) {
        session.setAttribute("sName", null);
        session.setAttribute("sPassword", null);
        session.setAttribute("list", null);
        logger.info("User logged out");
        return "login";
    }

}
