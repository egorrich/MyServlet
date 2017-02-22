package controller;

import model.User;
import services.UserDAO;
import services.UserDAOHibernateImpl;
import utils.SessionValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

//TODO: rename to /Home servlet
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

    private final static String TABLE_NAME = "Users";

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOHibernateImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printUsers(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printUsers(req, resp);
    }

    private void printUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionValidator.validate((String) req.getSession().getAttribute("sName"),
                (String) req.getSession().getAttribute("sPassword"))) {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            List<User> list = userDAO.findAll();
            Collections.sort(list);
            req.getSession().setAttribute("list", list);
            req.getSession().setAttribute("TableName", TABLE_NAME);
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}