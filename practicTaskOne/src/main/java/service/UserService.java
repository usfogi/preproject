package service;

import DAO.UserDAO;
import DAO.UserJdbcDAO;
import dataSet.UsersDataSet;
import util.DbHelper;

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

    private UserDAO userDAO = getUserDAO();

    private static UserJdbcDAO getUserDAO() {
        return new UserJdbcDAO(DbHelper.getMysqlConnection());
    }



    public void addUser(UsersDataSet user)  {
        try {
            userDAO.createTable();
            userDAO.addClient(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UsersDataSet> getAllUsers()  {
        try {
            return userDAO.getList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UsersDataSet getUserById(Long id) {
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(UsersDataSet user) {
        try {
            userDAO.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(UsersDataSet user) {
        try {
            userDAO.deleteUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


/*    public void cleanUp() {
        UserJdbcDAO dao = getUserDAO();
        try {
            userDAO.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        UserJdbcDAO dao = getUserDAO();
        try {
            userDAO.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

}


