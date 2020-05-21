package servlet;

import com.google.gson.Gson;
import model.Car;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(CarService.getInstance().getAllCars());
        resp.getWriter().write(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");


        CarService buyCar = CarService.getInstance();
        Car carCastomerWantsToBuy = new Car();
        carCastomerWantsToBuy.setBrand(brand);
        carCastomerWantsToBuy.setModel(model);
        carCastomerWantsToBuy.setLicensePlate(licensePlate);

        buyCar.dropCarFromBd(carCastomerWantsToBuy);
        resp.setStatus(200);



    }
}
