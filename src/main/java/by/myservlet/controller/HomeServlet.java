package by.myservlet.controller;

import by.myservlet.model.User;
import by.myservlet.services.UserDAO;
import by.myservlet.utils.SessionValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/HomeServlet")
@Component
public class HomeServlet extends HttpServlet {

    private final static String TABLE_NAME = "Users";


    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.findWebApplicationContext(getServletContext());
        userDAO = (UserDAO) context.getBean("userDAOHibernateImpl");
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