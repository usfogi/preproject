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

@WebServlet(urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpImplementation();
        response.setContentType("text/html");
        try {
            long id = Long.parseLong(request.getParameter("id"));
            UsersDataSet user;
            user = userService.getUserById(id);
            userService.deleteUser(user);
            request.setAttribute("userList", userService.getAllUsers());
            request.getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
