package DAO;

import model.Car;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllCars() {
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createQuery("FROM Car").list();
        transaction.commit();
        session.close();
        return cars;
    }

    public List<Car> getOneCar(Car car) {
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Car c WHERE c.licensePlate = :licensePlate");
        query.setParameter("licensePlate", car.getLicensePlate());
        List<Car> carFromBd = query.list();

        transaction.commit();
        session.close();
        return carFromBd;
    }

    public Long countBrand (Car car) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT COUNT(*) FROM Car item WHERE item.brand = :brand");
        query.setParameter("brand", car.getBrand());
        Long count = (Long) query.uniqueResult();
        transaction.commit();
        session.close();
        return count;
    }

    public void putCarToDbIn(Car car) {
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }

    public void dropLineInBd(Car car) {
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE Car WHERE id = :id");
        query.setParameter("id", car.getId());
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public void dropDb() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Car").executeUpdate();
        transaction.commit();
        session.close();
    }
}
