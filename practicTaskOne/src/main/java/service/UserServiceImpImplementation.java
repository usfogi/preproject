package service;

import DAO.UserDAO;
import dataSet.UsersDataSet;

import util.UserDAOFactory;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpImplementation implements UserService{

    private UserDAO userDAO = UserDAOFactory.getUserDAO();

    @Override
    public void addUser(UsersDataSet user)  {
        try {
            userDAO.createTable();
            userDAO.addClient(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UsersDataSet> getAllUsers()  {
        try {
            return userDAO.getList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UsersDataSet getUserById(Long id) {
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(UsersDataSet user) {
        try {
            userDAO.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(UsersDataSet user) {
        try {
            userDAO.deleteUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
