package by.myservlet.controller;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create on 15.2.17.

 * @author egor
 */
@WebServlet("/logout")
@Component
public class Logout extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("sName", null);
        req.getSession().setAttribute("sPassword", null);
        req.getSession().setAttribute("list", null);
        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("sName", null);
        req.getSession().setAttribute("sPassword", null);
        req.getSession().setAttribute("list", null);
        resp.sendRedirect("/login.jsp");
    }
}
