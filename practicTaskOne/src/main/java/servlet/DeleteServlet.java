package servlet;

import dataSet.UsersDataSet;
import service.UserService;

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
        response.setContentType("text/html");
        try {
            long id = Long.parseLong(request.getParameter("id"));
            UsersDataSet user;
            user = UserService.getInstance().getUserById(id);
            UserService.getInstance().deleteUser(user);
            request.setAttribute("userList", UserService.getInstance().getAllUsers());
            request.getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
