package dao;

import connection.ConnectionFactory;
import model.Request;
import model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {

    private ConnectionFactory connectionFactory;

    private static RequestDao instance = new RequestDao();

    public RequestDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    //TODO DEFINIR COMO SERÁ IMPLEMENTADO
    @Deprecated
    public List<Request> findRequestByVehicle(Vehicle vehicle) throws SQLException {
        Request request = new Request();
        List<Request> requests = new ArrayList<>();

        Connection connection = connectionFactory.connection();

        String querySql = "SELECT * FROM request WHERE plate=?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1, vehicle.getPlate());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            request.setStatus(resultSet.getInt("status"));
            request.setCustomer(UserDao.getInstance().findCustomerrById(resultSet.getInt("customer")));
            request.setWorker(UserDao.getInstance().findWorkerById(resultSet.getInt("worker")));
            request.setVehicle(VehicleDao.getInstance().findVehicleByPlate(resultSet.getString("vehicle_plate")));
            request.setArrivalDestination(AddressDao.getInstance().findAddressByAddressId(resultSet.getInt("arrival")));
            request.setDeparture(AddressDao.getInstance().findAddressByAddressId(resultSet.getInt("departure")));

            requests.add(request);
        }
        return requests;
    }

    public static RequestDao getInstance() {
        return instance;
    }
}
