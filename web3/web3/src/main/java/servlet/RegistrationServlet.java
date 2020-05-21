package servlet;

import exception.DBException;
import model.BankClient;
import service.BankClientService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {

//    BankClientService bankClientService = new BankClientService();

/*    public RegistrationServlet(BankClientService bankClientService) {
        this.bankClientService = bankClientService;
    }*/


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getInstance().getPage("registrationPage.html", new HashMap<>()));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> myMap = new HashMap<>();
        Boolean clientCreationCheck = false;

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Long money = Long.valueOf(req.getParameter("money"));
        try {
            clientCreationCheck = new BankClientService().addClient(new BankClient(name, password, money));
        } catch (DBException e) {
            System.out.println("Ошибка создания клиента в сервлете регистрации");
//            e.printStackTrace();
        }
        if (clientCreationCheck) {
            myMap.put("message", "Add client successful");
            resp.getWriter().println(PageGenerator.getInstance().getPage("resultPage.html", myMap));
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            myMap.put("message", "Client not add");
            resp.getWriter().println(PageGenerator.getInstance().getPage("resultPage.html", myMap));
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
