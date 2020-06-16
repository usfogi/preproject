package DAO;

import dataSet.UsersDataSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void addClient(UsersDataSet user) throws SQLException {
        try(PreparedStatement prepStat = connection.prepareStatement("INSERT INTO userList (name, password, age) VALUES (?, ?, ?)")) {
            prepStat.setString(1, user.getName());
            prepStat.setString(2, user.getPassword());
            prepStat.setLong(3, user.getAge());
            prepStat.execute();
        }
    }

    public List<UsersDataSet> getList() throws SQLException {
        try (Statement stmt = connection.createStatement()){
            stmt.executeQuery("SELECT * FROM userList");
            ResultSet resultSet = stmt.getResultSet();
            List<UsersDataSet> users = new LinkedList<>();
            while (resultSet.next()) {
                users.add(new UsersDataSet(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getLong("age")));
            }
            resultSet.close();
            return users;
        }
    }

    public UsersDataSet getUserById(Long id) throws SQLException {
        UsersDataSet usersDataSet = new UsersDataSet();
        try (PreparedStatement prersStmt = connection.prepareStatement("SELECT * FROM userList WHERE id = ?")){
            prersStmt.setString(1, String.valueOf(id));
            prersStmt.execute();
            ResultSet result = prersStmt.getResultSet();
            result.next();
            usersDataSet.setId(result.getLong("id"));
            usersDataSet.setName(result.getString("name"));
            usersDataSet.setPassword(result.getString("password"));
            usersDataSet.setAge(result.getLong("age"));
            result.close();
            return usersDataSet;
        }
    }

    public void updateUser(UsersDataSet user) throws SQLException {
        try (PreparedStatement prepStmt = connection.prepareStatement("UPDATE userList SET name = ?, password = ?, age = ? WHERE id = ?")){
            prepStmt.setString(1, user.getName());
            prepStmt.setString(2, user.getPassword());
            prepStmt.setLong(3, user.getAge());
            prepStmt.setLong(4, user.getId());
            prepStmt.execute();
        }
    }

    public void deleteUser(UsersDataSet user) throws SQLException {
        try (PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM userList WHERE id = ?")) {
            prepStmt.setLong(1, user.getId());
            prepStmt.execute();
        }
    }

    public void createTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS userList (id bigint auto_increment, name varchar(256), password varchar(256), age bigint, primary key (id))");
        }
    }

    public void dropTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS userList");
        }
    }
}
