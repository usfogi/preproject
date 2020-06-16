package servlet;

import dataSet.UsersDataSet;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/register")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userList", UserService.getInstance().getAllUsers());
        req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Long age = Long.valueOf(req.getParameter("age"));

        if (name != null && password != null && age != null) {
            UsersDataSet user = new UsersDataSet(name, password, age);
            UserService.getInstance().addUser(user);
        }
        resp.sendRedirect("/list");
    }


}