package dao;

import connection.ConnectionFactory;
import model.Address;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDao implements DAO<User>{

    private ConnectionFactory connectionFactory;

    public AddressDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public void save(User user) {
        Connection connection = connectionFactory.connection();

        String querySql =  "INSERT INTO address (city, state, street, number, USER_ID) " + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            preparedStatement.setString(1, user.getAddress().getCity());
            preparedStatement.setString(2, user.getAddress().getState());
            preparedStatement.setString(3, user.getAddress().getStreet());
            preparedStatement.setInt(4, user.getAddress().getNumber());
            preparedStatement.setInt(5, user.getUserId());

            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Address setAddress(User user) {
        Address address = new Address();

        Connection connection = connectionFactory.connection();

        String querySql = "SELECT state, city, street, number FROM address WHERE user_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setInt(1, user.getUserId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                address.setState(resultSet.getString("state"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setNumber(resultSet.getInt("number"));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return address;
    }

    @Override
    public void update(User user) {
        Connection connection = connectionFactory.connection();

        String querySql = "UPDATE address SET " +
                "state=?, city=?, street=?, number=? WHERE USER_ID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1, user.getAddress().getState());
            preparedStatement.setString(2,user.getAddress().getCity());
            preparedStatement.setString(3,user.getAddress().getStreet());
            preparedStatement.setInt(4,user.getAddress().getNumber());
            preparedStatement.setInt(5,user.getUserId());
            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        Connection connection = connectionFactory.connection();

        String querySql = "DELETE FROM address WHERE user_id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            preparedStatement.setInt( 1, user.getUserId());
            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}