package DAO;

import model.SoldCars;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SoldCarsDao {

    private Session session;

    public SoldCarsDao(Session session) {
        this.session = session;
    }


    public void putSoldCarToDbIn(SoldCars soldCar) {
        Transaction transaction = session.beginTransaction();
        session.save(soldCar);
        transaction.commit();
        session.close();
    }

    public List<SoldCars> getListSoldCarsOfDay() {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM SoldCars");
        List<SoldCars> cars = query.list();
        transaction.commit();
        session.close();
        return cars;
    }

    public void dropSoldCarsDb() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM SoldCars").executeUpdate();
        transaction.commit();
        session.close();
    }

}
