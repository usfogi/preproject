
import exception.DBException;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.BankClientService;
import model.BankClient;
import servlet.ApiServlet;
import servlet.MoneyTransactionServlet;
import servlet.RegistrationServlet;
import servlet.ResultServlet;



public class Main {
    public static void main(String[] args) throws Exception{
        ApiServlet apiServlet = new ApiServlet();
        RegistrationServlet registrationServlet = new RegistrationServlet();
        MoneyTransactionServlet moneyTransactionServlet = new MoneyTransactionServlet();
        ResultServlet resultServlet = new ResultServlet();

//        BankClientService bankClientService = new BankClientService();


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(apiServlet), "/api/*");

        context.addServlet(new ServletHolder(registrationServlet), "/registration");
        context.addServlet(new ServletHolder(moneyTransactionServlet), "/transaction");
        context.addServlet(new ServletHolder(resultServlet), "/result");



        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("templates");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler,context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
