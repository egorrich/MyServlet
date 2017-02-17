package controller;

import model.User;
import services.UserDAO;
import services.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by egor on 10.2.17.
 */

@WebServlet("/users")
public class UsersServlet extends HttpServlet{

    private final String TABLE_NAME = "Users";

    UserDAO userDAO;
    private String name;
    private String lastName;
    private int id;
    private String password;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String operation = req.getParameter("operation");
        if ((operation == null) || (operation.equals("update")) ) {
            id = Integer.parseInt(req.getParameter("id"));
            name = req.getParameter("name");
            lastName = req.getParameter("lastName");
            password = req.getParameter("password");
            if (operation == null) {
                User user = new User(id, name, lastName, password);
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/users.jsp");
            }
            else {
                String persistedUserName = getPersistedUserName();
                User user = new User(id, name, lastName, password);
                userDAO.update(user);
                req.getSession().setAttribute("user", null);
                String sName = (String) req.getSession().getAttribute("sName");
                if (persistedUserName.equals(sName)) {
                    req.getSession().setAttribute("sName", name);
                }

                //resp.sendRedirect("/MyServlet");
                req.getRequestDispatcher("/MyServlet").forward(req, resp);
            }

        }

        if (!(req.getParameter("operation") == null) && req.getParameter("operation").equals("delete") ) {
            id = Integer.parseInt(req.getParameter("id"));
            userDAO.delete(id);
            resp.sendRedirect("/MyServlet");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("operation").equals("create") ) {
            name = req.getParameter("name");
            lastName = req.getParameter("lastName");
            password = req.getParameter("password");
            userDAO.create(new User(name, lastName, password));

            resp.sendRedirect("/MyServlet");
        }

        /*if (req.getParameter("operation").equals("update") ) {
            id = Integer.parseInt(req.getParameter("id"));
            name = req.getParameter("name");
            lastName = req.getParameter("lastName");
            password = req.getParameter("password");

            String persistedUserName = getPersistedUserName();
            User user = new User(id, name, lastName, password);
            userDAO.update(user);

            String sName = (String) req.getSession().getAttribute("sName");
            if (persistedUserName.equals(sName)) {
                req.getSession().setAttribute("sName", name);
            }

            resp.sendRedirect("/MyServlet");
        }*/
    }

    private String getPersistedUserName() {
        User persistedUser = userDAO.findById(id);
        return persistedUser.getName();
    }
}
