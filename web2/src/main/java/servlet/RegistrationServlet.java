package servlet;

import model.User;
import service.UserService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {

    private final UserService userService;

    public RegistrationServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> register = new HashMap<>();
        resp.getWriter().println(PageGenerator.instance().getPage("registerPage.html", register));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.getInstance().addUser(new User(email, password));

        Map<String, Object> register = new HashMap<>();
        resp.getWriter().println(PageGenerator.instance().getPage("registerPage.html", register));
        resp.setContentType("text/html;charset=utf-8");

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
