package servlets;

import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HandlerGetServlet extends HttpServlet {



    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        try {
            int val = Integer.parseInt(request.getParameter("value"));
            response.getWriter().print(val * 2);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            response.getWriter().print(0);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

}
