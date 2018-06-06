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

    private AddressDao addressDao = new AddressDao();

    public UserDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public int lastUserId() {
        int userId = 0;

        Connection connection = connectionFactory.connection();

        String querySql = "SELECT user_id FROM cargapesada.user;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {userId = resultSet.getInt("user_id");}
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

        String querySql = "SELECT cpf, name, password, phone_number, user_id, username, rating, user_type " +
                "FROM user";

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
                    user.setUserType(UserType.CUSTOMER.getUserType());
                    userList.add(user);
                } else {
                    ((Worker) user).setRating(resultSet.getInt("rating"));
                    user.setUserType(UserType.WORKER.getUserType());
                    userList.add(user);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean returnAuthentication(String username, String password) {
        if (authenticateUser(username, password).getUserId()!=0) {
            return true;
        } else {return false;}
    }

    @Override
    public User authenticateUser(String username, String password) {
        User user = new User();

        Connection connection = connectionFactory.connection();

        String querySql = "SELECT user_id, name, cpf, username, user_type, username, password FROM user WHERE username = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setCpf(resultSet.getString("cpf"));
                if (resultSet.getInt("user_type") == 1) {
                    user.setUserType(UserType.CUSTOMER.getUserType());
                } else {user.setUserType(UserType.WORKER.getUserType());}
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setAddress(addressDao.setAddress(user));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        Connection connection = connectionFactory.connection();

        String querySql1 = "INSERT INTO user (name, cpf, username, password, phone_number, user_type)" +
                " VALUES (?, ?, ?, ?, ?, ?)";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql1);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getCpf());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPhoneNumber());
            if (user.getUserType() == UserType.WORKER) {
                preparedStatement.setInt(6, UserType.WORKER.getUserType());
                preparedStatement.execute();

                connection.close();
            } else if (user.getUserType() == UserType.CUSTOMER) {
                preparedStatement.setInt(6, UserType.CUSTOMER.getUserType());
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

        String querySql = "UPDATE user SET " +
                "name=?, password=?, phone_number=? WHERE user_id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setInt(4,user.getUserId());
            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        Connection connection = connectionFactory.connection();

        String querySql = "DELETE FROM user WHERE username = ? AND user_id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setInt(2,user.getUserId());
            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
