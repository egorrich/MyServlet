package controller;

import model.User;
import services.UserDAO;
import services.UserDAOHibernateImpl;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAOHibernateImpl();

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
