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

@WebServlet(urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpImplementation();
        response.setContentType("text/html");

        try {
            long id = Integer.parseInt(request.getParameter("id"));
            UsersDataSet user;
            user = userService.getUserById(id);
            request.setAttribute("user", user);
            request.getServletContext().getRequestDispatcher("/update.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpImplementation();
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Long age = Long.valueOf(request.getParameter("age"));

        if (id != null && name != null && password != null && age != null) {
            UsersDataSet user = new UsersDataSet(id, name, password, age);
            userService.updateUser(user);
        }
        response.sendRedirect("/list");
    }
}
