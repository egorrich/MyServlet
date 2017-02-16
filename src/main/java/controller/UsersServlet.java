package controller;

import model.User;
import services.IUserOperations;
import services.IUserOperationsImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

/**
 * Created by egor on 10.2.17.
 */

@WebServlet("/users")
public class UsersServlet extends HttpServlet{

    private final String TABLE_NAME = "Users";

    IUserOperations iUserOperations;
    private String name;
    private String lastName;
    private int id;
    private String password;

    @Override
    public void init() throws ServletException {
        iUserOperations = new IUserOperationsImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("operation").equals("delete") ) {
            id = Integer.parseInt(req.getParameter("id"));
            iUserOperations.delete(id);
            resp.sendRedirect("/MyServlet");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("operation").equals("create") ) {
            name = req.getParameter("name");
            lastName = req.getParameter("lastName");
            password = req.getParameter("password");
            iUserOperations.create(new User(name, lastName, password));

            resp.sendRedirect("/MyServlet");
        }

        if (req.getParameter("operation").equals("update") ) {
            id = Integer.parseInt(req.getParameter("id"));
            name = req.getParameter("name");
            lastName = req.getParameter("lastName");
            password = req.getParameter("password");

            String persistedUserName = getPersistedUserName();
            User user = new User(id, name, lastName, password);
            iUserOperations.update(user);

            String sName = (String) req.getSession().getAttribute("sName");
            if (persistedUserName.equals(sName)) {
                req.getSession().setAttribute("sName", name);
            }

            resp.sendRedirect("/MyServlet");
        }
    }

    private String getPersistedUserName() {
        User persistedUser = iUserOperations.findById(id);
        return persistedUser.getName();
    }
}
