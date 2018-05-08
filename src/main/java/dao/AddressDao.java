package dao;

import connection.ConnectionFactory;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {
    }
}
