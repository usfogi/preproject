package servlet;

import dataSet.UsersDataSet;
import service.UserService;
import service.UserServiceImpImplementation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/register")
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpImplementation();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Long age = Long.valueOf(req.getParameter("age"));

        if (name != null && password != null && age != null) {
            UsersDataSet user = new UsersDataSet(name, password, age);
            userService.addUser(user);
        }
        resp.sendRedirect("/list");
    }


}