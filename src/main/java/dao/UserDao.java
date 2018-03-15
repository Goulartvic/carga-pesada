package dao;

import connection.ConnectionFactory;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        return null;
    }

    @Override
    public User authenticateUser(String email, String password) {
        return null;
    }

    @Override
    public void save(User user) {
        Connection connection = connectionFactory.connection();

        String querySql = "INSERT INTO USER (USER_ID, USERNAME, PASSWORD, NAME, USER_TYPE_ID, CPF, PHONE_NUMBER, INTERCITY)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            setStatementPattern(user, preparedStatement);
            if (user instanceof Worker) {
                preparedStatement.setInt(5, UserType.WORKER.getUserType());
                preparedStatement.setString(6, ((Worker) user).getCpf());
                preparedStatement.setString(7, ((Worker) user).getPhoneNumber());
                preparedStatement.setBoolean(8, ((Worker) user).isIntercity());
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
}
