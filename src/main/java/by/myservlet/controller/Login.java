package by.myservlet.controller;

import by.myservlet.model.User;
import by.myservlet.services.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create on 14.2.17.
 *
 * @author egor
 */
@WebServlet("/login")
@Component
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
    }
}
