package DAO;

import dataSet.UsersDataSet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

/*
    private static SessionFactory sessionFactory;

    public UserHibernateDAO(Session sessionFactory) {
        UserHibernateDAO.sessionFactory = sessionFactory;
        sessionFactory.openSession();
    }
*/

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }


    @Override
    public void addClient(UsersDataSet user) throws SQLException {
//        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("\n ........Transaction AddClient Is Being Rolled Back........");
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<UsersDataSet> getList() throws SQLException {
        List<UsersDataSet> users = null;
//        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            users = session.createQuery("FROM UsersDataSet").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n ........Transaction GetList Is Being Rolled Back........");
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            return users;
        }
    }

    @Override
    public UsersDataSet getUserById(Long id) throws SQLException {
        UsersDataSet user = null;
//        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            user = (UsersDataSet) session.get(UsersDataSet.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n ........Transaction GetUserById Is Being Rolled Back........");
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            return user;
        }
    }

    @Override
    public void updateUser(UsersDataSet user) throws SQLException {
//        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n ........Transaction updateUser Is Being Rolled Back........");
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteUser(UsersDataSet user) throws SQLException {
//        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n ........Transaction deleteUser GetUserById Is Being Rolled Back........");
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void dropTable() throws SQLException {

    }
}
