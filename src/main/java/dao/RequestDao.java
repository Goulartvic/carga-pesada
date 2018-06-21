package dao;

import connection.ConnectionFactory;
import model.Request;
import model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequestDao {

    private ConnectionFactory connectionFactory;

    private static RequestDao instance = new RequestDao();

    public RequestDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public static RequestDao getInstance() {
        return instance;
    }

    public void addRequest(Request request) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "INSERT INTO request (customer, vehicle, worker, status, arrival, departure)" +
                "VALUES (?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setInt(1,request.getCustomer().getUserId());
        preparedStatement.setString(2,request.getVehicle().getPlate());
        preparedStatement.setInt(3,request.getWorker().getUserId());
        preparedStatement.setInt(4, Status.ONHOLD.getStatus());
        preparedStatement.setInt(5,AddressDao.getInstance().getAddressId(request.getArrivalDestination()));
        preparedStatement.setInt(6,AddressDao.getInstance().getAddressId(request.getDeparture()));
        preparedStatement.execute();

        connection.close();
    }
}
