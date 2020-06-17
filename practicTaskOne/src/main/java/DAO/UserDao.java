package DAO;

import dataSet.UsersDataSet;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void addClient(UsersDataSet user) throws SQLException;

    List<UsersDataSet> getList() throws SQLException;

    UsersDataSet getUserById(Long id) throws SQLException;

    void updateUser(UsersDataSet user) throws SQLException;

    void deleteUser(UsersDataSet user) throws SQLException;

    void createTable() throws SQLException;

    void dropTable() throws SQLException;
}
