package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public DailyReport getLastDayReport() {
        Transaction transaction = session.beginTransaction();
        DailyReport lastDayReport = (DailyReport) session.createQuery("FROM DailyReport ORDER BY id DESC").setMaxResults(1).uniqueResult();
        transaction.commit();
        session.close();
        return lastDayReport;
    }

    public void putOneDayReportToBd(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.save(dailyReport);
        transaction.commit();
        session.close();
    }

    public void dropReportDb() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM DailyReport").executeUpdate();
        transaction.commit();
        session.close();
    }
}
