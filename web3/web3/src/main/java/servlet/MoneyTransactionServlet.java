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

public class MoneyTransactionServlet extends HttpServlet {

     BankClientService bankClientService = new BankClientService();

//    public MoneyTransactionServlet(BankClientService bankClientService) {
//        this.bankClientService = bankClientService;
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getInstance().getPage("moneyTransactionPage.html", new HashMap<>()));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> myMap = new HashMap<>();
        Boolean sendMoneyCheck = false;

        String senderName = req.getParameter("senderName");
        String senderPass = req.getParameter("senderPass");
        Long count = Long.valueOf(req.getParameter("count"));
        String nameTo = req.getParameter("nameTo");
        BankClient client = new BankClient();
        client.setName(senderName);
        client.setPassword(senderPass);
        try {
            sendMoneyCheck =bankClientService.sendMoneyToClient(client, nameTo, count);
        } catch (DBException e) {
            e.printStackTrace();
        }

        if (sendMoneyCheck) {
            myMap.put("message", "The transaction was successful");

            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            myMap.put("message", "transaction rejected");
            /*resp.getWriter().println(PageGenerator.getInstance().getPage("resultPage.html", myMap));
            resp.setContentType("text/html;charset=utf-8");*/
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(PageGenerator.getInstance().getPage("resultPage.html", myMap));

    }
}
