package service;

import DAO.SoldCarsDao;
import model.DailyReport;
import model.SoldCars;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;
import java.util.ListIterator;

public class SoldCarsService {

    private static SoldCarsService soldCarsService;

    private SessionFactory sessionFactory;

    private SoldCarsService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static SoldCarsService getInstance() {
        if (soldCarsService == null) {
            soldCarsService = new SoldCarsService(DBHelper.getSessionFactory());
        }
        return soldCarsService;
    }

    public void insertPriceDateToDb(SoldCars soldCar) {
        try {
            new SoldCarsDao(sessionFactory.openSession()).putSoldCarToDbIn(soldCar);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public DailyReport getAllSoldCars() {

        List<SoldCars> list = new SoldCarsDao(sessionFactory.openSession()).getListSoldCarsOfDay();
        Long sumPriceSoldCarsForDay = Long.valueOf(0);
        Long numberOfSoldCarsForDay = Long.valueOf(0);
        ListIterator<SoldCars> listIterator = list.listIterator();
        while (listIterator.hasNext()) {

            sumPriceSoldCarsForDay += listIterator.next().getPrice();
            numberOfSoldCarsForDay += 1;
        }
        DailyReport dailyReport = new DailyReport(sumPriceSoldCarsForDay, numberOfSoldCarsForDay);
        return dailyReport;
    }

    public void dropListSoldCars() {
        new SoldCarsDao(sessionFactory.openSession()).dropSoldCarsDb();
    }
}

