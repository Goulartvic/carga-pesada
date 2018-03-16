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

        String querySql = "SELECT U.USER_ID, U.USERNAME, U.PASSWORD, U.FIRST_NAME, U.USER_TYPE_ID, U.CPF, U.PHONE_NUMBER, " +
                "A.STATE, A.CITY, A.STREET, A.HOUSE_NUMBER FROM USER U JOIN ADDRESS A ON U.USER_ID = A.USER_ID";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                searchAllPattern(user, resultSet);
                if (user instanceof Worker) {
                    user.setUserType(UserType.WORKER);
                    ((Worker) user).setCpf(resultSet.getString("CPF"));
                    ((Worker) user).setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                    ((Worker) user).getAddress().setState(resultSet.getString("STATE"));
                    ((Worker) user).getAddress().setCity(resultSet.getString("CITY"));
                    ((Worker) user).getAddress().setStreet(resultSet.getString("STREET"));
                    ((Worker) user).getAddress().setNumber(resultSet.getInt("HOUSE_NUMBER"));

                    userList.add(user);
                } else if (user instanceof Customer) {
                    user.setUserType(UserType.CUSTOMER);
                    ((Customer) user).setCpf(resultSet.getString("CPF"));
                    ((Customer) user).setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                    ((Customer) user).getAddress().setState(resultSet.getString("STATE"));
                    ((Customer) user).getAddress().setCity(resultSet.getString("CITY"));
                    ((Customer) user).getAddress().setStreet(resultSet.getString("STREET"));
                    ((Customer) user).getAddress().setNumber(resultSet.getInt("HOUSE_NUMBER"));

                    userList.add(user);
                } else {
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

        String querySql = "INSERT INTO USER (USER_ID, USERNAME, PASSWORD, FIRST_NAME, USER_TYPE_ID, CPF, PHONE_NUMBER)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            setStatementPattern(user, preparedStatement);
            if (user instanceof Worker) {
                preparedStatement.setInt(5, UserType.WORKER.getUserType());
                preparedStatement.setString(6, ((Worker) user).getCpf());
                preparedStatement.setString(7, ((Worker) user).getPhoneNumber());
                preparedStatement.execute();

                connection.close();
            } else if (user instanceof Customer) {
                preparedStatement.setInt(5, UserType.CUSTOMER.getUserType());
                preparedStatement.setString(6, ((Customer) user).getCpf());
                preparedStatement.setString(7, ((Customer) user).getPhoneNumber());
                preparedStatement.execute();

                connection.close();
            } else {
                preparedStatement.setInt(5, UserType.ADMIN.getUserType());
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
                "PASSWORD = ? WHERE USER_ID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1,user.getPassword());
            preparedStatement.setInt(2,user.getUserId());
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

    private PreparedStatement setStatementPattern(User user, PreparedStatement preparedStatement) {

        try {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    private User searchAllPattern (User user, ResultSet resultSet) {

        try {
            user.setUserId(resultSet.getInt("USER_ID"));
            user.setUsername(resultSet.getString("USERNAME"));
            user.setName(resultSet.getString("PASSWORD"));
            user.setName(resultSet.getString("FIRST_NAME"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
