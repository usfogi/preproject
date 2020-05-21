package dao;

//import com.sun.deploy.util.SessionState;
import model.BankClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankClientDAO {

    private Connection connection;
    private static final String UPDATE_MONEY = "UPDATE bank_client SET money = ? WHERE name = ? AND password = ?";
    private static final String VAL_CLIENT = "SELECT name, password FROM bank_client WHERE name = ? AND password = ?";

    public BankClientDAO(Connection connection) {
        this.connection = connection;
    }

    public List<BankClient> getAllBankClient() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM bank_client");
        ResultSet result = stmt.getResultSet();
        List<BankClient> clientList = new ArrayList<>();
        while (result.next()) {
            BankClient client = new BankClient();
            client.setId(result.getLong("id"));
            client.setName(result.getString("name"));
            client.setPassword(result.getString("password"));
            client.setMoney(result.getLong("money"));
            clientList.add(client);
        }
        result.close();
        stmt.close();
        return clientList;
    }

    public boolean validateClient(String name, String password)  throws SQLException {
        PreparedStatement prepStat = connection.prepareStatement(VAL_CLIENT);
        prepStat.setString(1, name);
        prepStat.setString(2, password);
        prepStat.execute();
        ResultSet result = prepStat.getResultSet();

        if (result.next()) {

            prepStat.close();
            return true;
        }
        prepStat.close();
        return false;
    }
/*
    public boolean validateClient(String name, String password)  throws SQLException {
*/
/*        BankClient baseClient = getClientByName(name);
        BankClient formClient = new BankClient(name, password);*//*

        Statement stmt = connection.createStatement();
        stmt.execute("SELECT name, password FROM bank_client WHERE name='" + name + "' AND password='" + password + "'");
        ResultSet result = stmt.getResultSet();


        if (result.next()) {

            stmt.close();
            return true;
        }
        stmt.close();
        return false;
    }
*/

     public boolean validateClientByName(String name)  throws SQLException {
         Statement stmt = connection.createStatement();
         stmt.execute("SELECT name, password FROM bank_client WHERE name='" + name + "'");
         ResultSet result = stmt.getResultSet();

         if (result.next()) {
             stmt.close();
             return true;
         }
         stmt.close();
         return false;
    }



/*    public void updateClientsMoney(String name, String password, Long transactValue) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("UPDATE bank_client SET money=" + transactValue + " WHERE name='" + name + "' AND password='" + password + "'");
        stmt.close();
    }*/
    public void updateClientsMoney(String name, String password, Long transactValue) throws SQLException {
        PreparedStatement prepStat = connection.prepareStatement(UPDATE_MONEY);
        prepStat.setLong(1, transactValue);
        prepStat.setString(2, name);
        prepStat.setString(3, password);
        prepStat.execute();
        prepStat.close();
    }

    public BankClient getClientById(long id) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM bank_client WHERE id='" + id + "'");
        ResultSet result = stmt.getResultSet();
        result.next();
        long getIdFromDB = result.getLong("id");
        String name = result.getString("name");
        String password = result.getString("password");
        Long money = result.getLong("money");
        result.close();
        stmt.close();
        return new BankClient(getIdFromDB, name, password, money);
    }

    public boolean isClientHasSum(String name, Long expectedSum) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT money FROM bank_client WHERE name='" + name + "'");
        ResultSet result = stmt.getResultSet();
        result.next();
        Long moneyOnDeposit = result.getLong("money");
        result.close();
        stmt.close();
        if (moneyOnDeposit >= expectedSum) {
            return true;
        }
        return false;
    }

    public long getClientIdByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM bank_client WHERE name='" + name + "'");
        ResultSet result = stmt.getResultSet();
        result.next();
        Long id = result.getLong(1);
        result.close();
        stmt.close();
        return id;
    }

     public Long getDepositMountByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM bank_client WHERE name='" + name + "'");
        ResultSet result = stmt.getResultSet();
        result.next();
        Long money = result.getLong("money");
        result.close();
        stmt.close();
        return money;
    }

    public BankClient getClientByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM bank_client WHERE name='" + name + "'");
        ResultSet result = stmt.getResultSet();
        result.next();
        long id = result.getLong("id");
        String getNameFromDB = result.getString("name");
        String password = result.getString("password");
        Long money = result.getLong("money");
        result.close();
        stmt.close();
        return new BankClient(id, getNameFromDB, password, money);
    }

    public void addClient(BankClient client) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("INSERT INTO bank_client (name, password, money) VALUES ('" + client.getName() + "','" + client.getPassword() + "'," + client.getMoney() + ")");
        stmt.close();
    }

//    public boolean annihilateClient(String name) throws SQLException {
//        Statement stmt = connection.createStatement();
//        Boolean destroyedClientOrNot = stmt.execute("DELETE FROM bank_client WHERE name='" + name + "'");
//        stmt.close();
//        return destroyedClientOrNot;
//    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS bank_client (id bigint auto_increment, name varchar(256), password varchar(256), money bigint, primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS bank_client");
        stmt.close();
    }
}
