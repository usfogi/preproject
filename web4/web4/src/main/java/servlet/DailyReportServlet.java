package servlet;

import com.google.gson.Gson;
import service.CarService;
import service.DailyReportService;
import service.SoldCarsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DailyReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();

        if (req.getPathInfo().contains("all")) {
            String json = gson.toJson(DailyReportService.getInstance().getAllDailyReports());
            resp.getWriter().write(json);
            resp.setStatus(200);
        } else if (req.getPathInfo().contains("last")) {
            String json = gson.toJson(DailyReportService.getInstance().getLastReport());
            resp.getWriter().write(json);
            resp.setStatus(200);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarService carService = CarService.getInstance();
        DailyReportService dailyReportService = DailyReportService.getInstance();
        SoldCarsService soldCarsService = SoldCarsService.getInstance();
        carService.dropCarDb();
        dailyReportService.dropDailyReportDb();
        soldCarsService.dropListSoldCars();
    }
}
