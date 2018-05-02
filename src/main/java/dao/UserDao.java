package dao;

import connection.ConnectionFactory;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDaoInterface{

    private ConnectionFactory connectionFactory;

    public UserDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public List<User> searchAll() {
        List<User> userList = new ArrayList<>();

        Connection connection = connectionFactory.connection();

        String querySql = "SELECT CPF, NAME, PASSWORD, PHONE_NUMBER, USERNAME, RATING, USER_TYPE" +
                "FROM USER";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setCpf(resultSet.getString("cpf"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setPhoneNumber(resultSet.getString("user_id"));
                user.setUsername(resultSet.getString("username"));

                if (resultSet.getInt("user_type") == 1) {
                    user.setUserType(UserType.CUSTOMER);
                    userList.add(user);
                } else {
                    ((Worker) user).setRating(resultSet.getInt("rating"));
                    user.setUserType(UserType.WORKER);
                    userList.add(user);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User authenticateUser(String email, String password) {
        return null;
    }

    @Override
    public void save(User user) {
        Connection connection = connectionFactory.connection();

        String querySql = "INSERT INTO USER (U.NAME, U.CPF, U.USERNAME, U.PASSWORD, U.USER_TYPE," +
                "A.CIDADE, A.ESTADO, A.RUA, A.NUMERO"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            preparedStatement.setString(1, user.getCpf());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setInt(5, user.getUserId());
            preparedStatement.setString(6, user.getUsername());

            if (user instanceof Worker) {
                preparedStatement.setInt(7, UserType.WORKER.getUserType());
                preparedStatement.execute();

                connection.close();
            } else if (user instanceof Customer) {
                preparedStatement.setInt(7, UserType.CUSTOMER.getUserType());
                preparedStatement.execute();

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        Connection connection = connectionFactory.connection();

        String querySql = "UPDATE USER" +
                "NAME=?, PASSWORD = ? WHERE USER_ID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setInt(3,user.getUserId());
            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        Connection connection = connectionFactory.connection();

        String querySql = "DELETE FROM USER WHERE USER_ID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
