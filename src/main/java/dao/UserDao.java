package dao;

import connection.ConnectionFactory;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao{

    private ConnectionFactory connectionFactory;

    private static UserDao instance = new UserDao();

    public UserDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public int lastUserId() throws SQLException {
        int userId = 0;

        Connection connection = connectionFactory.connection();

        String querySql = "SELECT user_id FROM cargapesada.user;";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {userId = resultSet.getInt("user_id");}
        connection.close();

        return userId;
    }

    public List<User> searchAll() throws SQLException {
        List<User> userList = new ArrayList<>();

        Connection connection = connectionFactory.connection();

        String querySql = "SELECT cpf, name, password, phone_number, user_id, username, rating, user_type " +
                "FROM user";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User user;
            if (resultSet.getInt("user_type") == 1) {
                user = new Customer();
            }
            else {
                user = new Worker();
                ((Worker) user).setRating(resultSet.getInt("rating"));
            }
            user.setCpf(resultSet.getString("cpf"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setPhoneNumber(resultSet.getString("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setUserType(resultSet.getInt("user_type"));

            userList.add(user);
        }
        connection.close();

        return userList;
    }

    public boolean returnAuthentication(String username, String password) throws SQLException {
        return authenticateUser(username, password).getUserId() != 0;
    }

    public User authenticateUser(String username, String password) throws SQLException {
        User user = null;

        Connection connection = connectionFactory.connection();

        String querySql = "SELECT * FROM user WHERE username = ? AND password = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            if (resultSet.getInt("user_type") == 1) {
                user = new Customer();
                ((Customer) user).setRequests(RequestDao.getInstance().listRequests(resultSet.getInt("user_id")));
            }
            else {
                user = new Worker();
                ((Worker) user).setRating(resultSet.getDouble("rating"));
                ((Worker) user).setVehicles(VehicleDao.getInstance().listWorkerVehicles(resultSet.getInt("user_id")));
            }
            user.setUserId(resultSet.getInt("user_id"));
            user.setName(resultSet.getString("name"));
            user.setCpf(resultSet.getString("cpf"));
            user.setUserType(resultSet.getInt("user_type"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
        }
        connection.close();

        return user;
    }

    public void save(User user) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql1 = "INSERT INTO user (name, cpf, username, password, phone_number, user_type)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql1);

        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getCpf());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getPhoneNumber());
        preparedStatement.setInt(6, user.getUserType().getUserType());

        preparedStatement.execute();
        connection.close();
    }

    public void update(User user) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "UPDATE user SET " +
                "name=?, password=?, phone_number=? WHERE user_id=?";


        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3, user.getPhoneNumber());
        preparedStatement.setInt(4,user.getUserId());
        preparedStatement.execute();

        connection.close();

    }

    public void delete(User user) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "DELETE FROM user WHERE username = ? AND user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setInt(2,user.getUserId());
        preparedStatement.execute();

        connection.close();
    }

    public boolean userExist(String login) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "SELECT * FROM user WHERE username=?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            connection.close();
            return false;
        }
        connection.close();
        return true;
    }

    public Worker findWorkerById(int id) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "SELECT * FROM user WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Worker worker = new Worker();

        if (resultSet.next()) {
            worker.setUserId(resultSet.getInt("user_id"));
            worker.setName(resultSet.getString("name"));
            worker.setCpf(resultSet.getString("cpf"));
            worker.setUsername(resultSet.getString("username"));
            worker.setUserType(resultSet.getInt("user_type"));
            worker.setPassword(resultSet.getString("password"));
            worker.setRating(resultSet.getDouble("rating"));
            worker.setPhoneNumber("phone_number");
        }
        connection.close();

        return worker;
    }

    public List<Worker> searchWorkers(String city, double rating) throws SQLException {
        Connection connection = connectionFactory.connection();

        List<Worker> workerList = new ArrayList<>();

        String querySql = "SELECT * FROM user u JOIN address a ON u.user_id=a.user_id " +
                "WHERE a.city like ? AND u.rating>=? AND u.user_type=2";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setString(1, city);
        preparedStatement.setDouble(2, rating);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Worker worker = new Worker();
            worker.setRating(resultSet.getDouble("rating"));
            worker.setName(resultSet.getString("name"));
            worker.setPhoneNumber(resultSet.getString("phone_number"));
            worker.setPassword(resultSet.getString("password"));
            worker.setUsername(resultSet.getString("username"));
            worker.setUserType(resultSet.getInt("user_type"));
            worker.setCpf(resultSet.getString("cpf"));
            worker.setUserId(resultSet.getInt("user_id"));
            worker.getAddress().setState(resultSet.getString("state"));
            worker.getAddress().setCity(resultSet.getString("city"));
            worker.getAddress().setStreet(resultSet.getString("street"));
            worker.getAddress().setNumber(resultSet.getInt("number"));

            workerList.add(worker);
        }

        return workerList;
    }

    public Customer findCustomerrById(int id) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "SELECT * FROM user WHERE user_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Customer customer = new Customer();

        if (resultSet.next()) {
            customer.setUserId(resultSet.getInt("user_id"));
            customer.setName(resultSet.getString("name"));
            customer.setCpf(resultSet.getString("cpf"));
            customer.setUsername(resultSet.getString("username"));
            customer.setUserType(resultSet.getInt("user_type"));
            customer.setPassword(resultSet.getString("password"));
            customer.setPhoneNumber("phone_number");
        }
        connection.close();

        return customer;
    }

    public static UserDao getInstance() {
        return instance;
    }
}
