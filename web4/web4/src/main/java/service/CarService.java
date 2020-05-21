package service;

import DAO.CarDao;
import model.Car;
import model.SoldCars;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

import static servlet.NewDayServlet.dayCounter;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    public static Long checkAbsenceDailySales = Long.valueOf(0);

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllCars();
    }

    public Boolean insertNewCarsToDb(Car car) {
        Long quantityThisBrandInDb = new CarDao(sessionFactory.openSession()).countBrand(car);
        if (quantityThisBrandInDb > 9) {
            return false;
        }

        try {
            new CarDao(sessionFactory.openSession()).putCarToDbIn(car);
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    public void dropCarFromBd(Car car) {
        List<Car> cars = new CarDao(sessionFactory.openSession()).getOneCar(car);

        cars.forEach(carToDel -> {
            if (carToDel.equals(car)) {
                SoldCarsService soldCarsService = SoldCarsService.getInstance();
                soldCarsService.insertPriceDateToDb(new SoldCars(carToDel.getPrice(), dayCounter));
                checkAbsenceDailySales += 1;

                new CarDao(sessionFactory.openSession()).dropLineInBd(carToDel);
            }
        });
    }

    public void dropCarDb() {
        new CarDao(sessionFactory.openSession()).dropDb();
    }


}
