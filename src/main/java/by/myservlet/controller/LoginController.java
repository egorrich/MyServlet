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

/**
 * Create on 01.03.17.
 *
 * @author egor
 */
@Controller
@RequestMapping(value = "/LoginForm")
public class LoginController {

    Logger log = LoggerFactory.getLogger(LoginController.class.getName());

    @Autowired
    UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doLogin(@RequestParam("name") String name, @RequestParam("password") String password, Model model) {
        User user = userDAO.findByName(name);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                log.info("User logged in: " + user);
                model.addAttribute("sName", name);
                model.addAttribute("sPassword", password);
                model.addAttribute("list", null);
                return "home";
            }
        }
        return "login";
    }
}
/**
public class Login extends HttpServlet {

    Logger log = LoggerFactory.getLogger(Login.class.getName());

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.findWebApplicationContext(getServletContext());
        userDAO = (UserDAO) context.getBean("userDAOHibernateImpl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            User user = userDAO.findByName(name);
            log.info("User logged in: " + user);
            if (user.getPassword().equals(password)) {
                req.getSession().setAttribute("sName", name);
                req.getSession().setAttribute("sPassword", password);
                req.getSession().setAttribute("list", null);
                resp.sendRedirect("/home.jsp");
            } else {
                resp.sendRedirect("/login.jsp");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            resp.sendRedirect("/login.jsp");
        }
    }*/
