package service;

import DAO.DailyReportDao;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

import static service.CarService.checkAbsenceDailySales;
import static servlet.NewDayServlet.dayCounter;

public class DailyReportService {

    private static Long nextDayChecker = Long.valueOf(0);

    private static DailyReportService dailyReportService;

    private SessionFactory sessionFactory;

    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {

        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    public List<DailyReport> getAllDailyReports() {
        getLastReport();
        return new DailyReportDao(sessionFactory.openSession()).getAllDailyReport();
    }

    public DailyReport getLastReport() {
        if (nextDayChecker != dayCounter && checkAbsenceDailySales != 0) {
            putLineInReportsDb();
            nextDayChecker = dayCounter;
            checkAbsenceDailySales = Long.valueOf(0);
            return new DailyReportDao(sessionFactory.openSession()).getLastDayReport();
        }
        return new DailyReport(Long.valueOf(0),Long.valueOf(0));
    }

    public void putLineInReportsDb() {
        SoldCarsService soldCarsService = SoldCarsService.getInstance();
        DailyReport dailyReport = soldCarsService.getAllSoldCars();
        new DailyReportDao(sessionFactory.openSession()).putOneDayReportToBd(dailyReport);
    }

    public void dropDailyReportDb() {
        new DailyReportDao(sessionFactory.openSession()).dropReportDb();
    }
}
