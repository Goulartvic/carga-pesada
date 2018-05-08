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

    public int lastUserId() {
        int userId = 0;

        Connection connection = connectionFactory.connection();

        String querySql = "select count(user_id) from user";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("count(user_id)");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return userId;
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

        String querySql1 = "INSERT INTO user (name, cpf, username, password, user_type)" +
                " VALUES (?, ?, ?, ?, ?)";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql1);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getCpf());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());

            if (user.getUserType() == UserType.WORKER) {
                preparedStatement.setInt(5, UserType.WORKER.getUserType());
                preparedStatement.execute();

                connection.close();
            } else if (user.getUserType() == UserType.CUSTOMER) {
                preparedStatement.setInt(5, UserType.CUSTOMER.getUserType());
                preparedStatement.execute();

                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateAddress(User user) {
        Connection connection = connectionFactory.connection();

        String querySql = "UPDATE ADDRESS" + "STATE = ?, CITY = ?, STREET = ?, NUMBER = ? WHERE USER_ID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1, user.getAddress().getState());
            preparedStatement.setString(2, user.getAddress().getCity());
            preparedStatement.setString(3, user.getAddress().getStreet());
            preparedStatement.setInt(4, user.getAddress().getNumber());
            preparedStatement.setInt(5, user.getUserId());

            preparedStatement.execute();
            connection.close();
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
            updateAddress(user);
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
    }
}
