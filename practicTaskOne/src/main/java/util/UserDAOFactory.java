package util;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static util.DbHelper.getSessionFactory;

public class UserDAOFactory {

    public static UserDAO getUserDAO() {
        Properties properties = new Properties();
        try (InputStream stream = DbHelper.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (properties.getProperty("Dao").equals("Hibernate")) {
            return new UserHibernateDAO(getSessionFactory().openSession());
        } else {
            return new UserJdbcDAO(DbHelper.getMysqlConnection());
        }
    }
}
