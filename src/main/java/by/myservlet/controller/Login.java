package by.myservlet.controller;

import by.myservlet.model.User;
import by.myservlet.services.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

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
public class Login extends HttpServlet {

    private final UserDAO userDAO;

    @Autowired
    public Login(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            User user = userDAO.findByName(name);
            System.out.println(user);
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
