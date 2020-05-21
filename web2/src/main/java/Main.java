import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import service.UserService;
import servlet.ApiServlet;
import servlet.LoginServlet;
import servlet.RegistrationServlet;

public class Main {

    public static void main(String[] args) throws Exception{
        ApiServlet apiServlet = new ApiServlet();
        UserService userService = UserService.getInstance();//new UserService();


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(apiServlet), "/api/*");

        context.addServlet(new ServletHolder(new RegistrationServlet(userService)), "/register");
        context.addServlet(new ServletHolder(new LoginServlet(userService)), "/login");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("templates");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
