package service;

import dao.BankClientDAO;
import exception.DBException;
import model.BankClient;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class BankClientService {

/*    private static BankClientService bankClientService;

    public static BankClientService getInstance(){
        if (bankClientService == null) {
            bankClientService = new BankClientService();
        }
        return bankClientService;
    }*/

    public BankClientService() {
    }

    public BankClient getClientById(long id) throws DBException {
        BankClientDAO dao = getBankClientDAO();
        try {
            return dao.getClientById(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public BankClient getClientByName(String name) throws DBException {
        BankClientDAO dao = getBankClientDAO();
        try {
            return dao.getClientByName(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }

    }

    public List<BankClient> getAllClient() throws DBException {
        BankClientDAO dao = getBankClientDAO();
        try {
            return dao.getAllBankClient();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

//    public boolean deleteClient(String name) throws DBException {
//        try {
//            return getBankClientDAO().annihilateClient(name);
//        } catch (SQLException e) {
//            throw new DBException(e);
//        }
//    }

    public boolean addClient(BankClient client) throws DBException {
        BankClientDAO dao = getBankClientDAO();
        Boolean clientExistingCheck = null;
        try {
//            clientExistingCheck = dao.validateClientByName(client.getName());
            clientExistingCheck = dao.validateClientByName(client.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (!clientExistingCheck) {
                getBankClientDAO().addClient(client);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DBException(e);
//            e.printStackTrace();
        }
//        return false;
    }

    public boolean sendMoneyToClient(BankClient sender, String name, Long value) throws DBException {
        try {
            Boolean senderExistingCheck = getBankClientDAO().validateClient(sender.getName(), sender.getPassword());
            Boolean recipientExistingCheck = getBankClientDAO().validateClientByName(name);
            if (senderExistingCheck || recipientExistingCheck) {
                BankClient baseSender = getBankClientDAO().getClientByName(sender.getName());
                if (!baseSender.equals(sender)) {
                    return false;
                }
                if (getBankClientDAO().isClientHasSum(sender.getName(), value)) {
                    Long depositAmount = getBankClientDAO().getDepositMountByName(name);
                    depositAmount -= value;
                    getBankClientDAO().updateClientsMoney(sender.getName(), sender.getPassword(), depositAmount);
                    BankClient recipientClient = getBankClientDAO().getClientByName(name);
                    value += recipientClient.getMoney();
                    getBankClientDAO().updateClientsMoney(recipientClient.getName(), recipientClient.getPassword(), value);
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e);
//            e.printStackTrace();
        }
//        return false;
    }

    public void cleanUp() throws DBException {
        BankClientDAO dao = getBankClientDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void createTable() throws DBException{
        BankClientDAO dao = getBankClientDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());    //"com.mysql.jdbc.Driver" //com.mysql.cj.jdbc.Driver

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=rootroot");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static BankClientDAO getBankClientDAO() {
        return new BankClientDAO(getMysqlConnection());
    }
}
