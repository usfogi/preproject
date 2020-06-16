package service;

import DAO.UserDao;
import dataSet.UsersDataSet;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class UserService {

    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService(){}

    public void addUser(UsersDataSet user)  {
        UserDao dao = getUserDAO();
        try {
            dao.createTable();
            dao.addClient(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UsersDataSet> getAllUsers()  {
        UserDao dao = getUserDAO();
        try {
            return dao.getList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UsersDataSet getUserById(Long id) {
        UserDao dao = getUserDAO();
        try {
            return dao.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(UsersDataSet user) {
        UserDao dao = getUserDAO();
        try {
            dao.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(UsersDataSet user) {
        UserDao dao = getUserDAO();
        try {
            dao.deleteUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void cleanUp() {
        UserDao dao = getUserDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        UserDao dao = getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("db_example?").
                    append("user=root&").
                    append("password=rootroot");

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDao getUserDAO() {
        return new UserDao(getMysqlConnection());
    }
}


