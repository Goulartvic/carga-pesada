package dao;

import connection.ConnectionFactory;
import model.Address;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDao {

    private ConnectionFactory connectionFactory;
    private static AddressDao instance = new AddressDao();

    public AddressDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public void save(User user) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql =  "INSERT INTO address (city, state, street, number, USER_ID) " + "VALUES (?, ?, ?, ?, ?)";


            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            preparedStatement.setString(1, user.getAddress().getCity());
            preparedStatement.setString(2, user.getAddress().getState());
            preparedStatement.setString(3, user.getAddress().getStreet());
            preparedStatement.setInt(4, user.getAddress().getNumber());
            preparedStatement.setInt(5, user.getUserId());

            preparedStatement.execute();
            connection.close();
    }

    public Address findAddressByAddressId(int id) throws SQLException {
        Address address = new Address();

        Connection connection = connectionFactory.connection();

        String querySql = "SELECT * FROM address WHERE address_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            address.setState(resultSet.getString("state"));
            address.setCity(resultSet.getString("city"));
            address.setStreet(resultSet.getString("street"));
            address.setNumber(resultSet.getInt("number"));
        }

        connection.close();

        return address;
    }


    public Address findAddressByUserId(User user) throws SQLException {
        Address address = new Address();

        Connection connection = connectionFactory.connection();

        String querySql = "SELECT state, city, street, number FROM address WHERE user_id = ?";

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

        return address;
    }

    public void update(User user) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "UPDATE address SET " +
                "state=?, city=?, street=?, number=? WHERE USER_ID = ?";


            PreparedStatement preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1, user.getAddress().getState());
            preparedStatement.setString(2,user.getAddress().getCity());
            preparedStatement.setString(3,user.getAddress().getStreet());
            preparedStatement.setInt(4,user.getAddress().getNumber());
            preparedStatement.setInt(5,user.getUserId());
            preparedStatement.execute();

            connection.close();
    }

    public void delete(User user) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "DELETE FROM address WHERE user_id=?";


            PreparedStatement preparedStatement = connection.prepareStatement(querySql);

            preparedStatement.setInt( 1, user.getUserId());
            preparedStatement.execute();

            connection.close();
    }

    public static AddressDao getInstance() {
        return instance;
    }
}
