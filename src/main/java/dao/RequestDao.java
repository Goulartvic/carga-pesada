package dao;

import connection.ConnectionFactory;
import model.Customer;
import model.Request;
import model.Status;
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

    public static RequestDao getInstance() {
        return instance;
    }

    public void update(Request request) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "UPDATE request SET status=? WHERE vehicle=? " +
                "AND customer=? AND worker=? AND arrival=? AND departure=?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setInt(1,request.getStatus().getStatus());
        preparedStatement.setString(2,request.getVehicle().getPlate());
        preparedStatement.setInt(3,request.getCustomer().getUserId());
        preparedStatement.setInt(4,request.getWorker().getUserId());
        preparedStatement.setInt(5,AddressDao.getInstance().getAddressId(request.getArrivalDestination()));
        preparedStatement.setInt(6,AddressDao.getInstance().getAddressId(request.getDeparture()));
        preparedStatement.execute();

        connection.close();
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

    public List<Request> listRequests(Vehicle vehicle) throws SQLException {
        Connection connection = connectionFactory.connection();
        List<Request> requestList = new ArrayList<>();

        String querySql = "SELECT * FROM request WHERE vehicle=?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setString(1, vehicle.getPlate());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Request request = new Request();
            request.setDeparture(AddressDao.getInstance().findAddressByAddressId(resultSet.getInt("departure")));
            request.setArrivalDestination(AddressDao.getInstance().findAddressByAddressId(resultSet.getInt("arrival")));
            request.setVehicle(vehicle);
            request.setWorker(UserDao.getInstance().findWorkerById(resultSet.getInt("worker")));
            request.setCustomer(UserDao.getInstance().findCustomerrById(resultSet.getInt("customer")));
            request.setStatus(resultSet.getInt("status"));
            requestList.add(request);
        }
        return requestList;
    }

    public List<Request> listRequests(int id) throws SQLException {
        Connection connection = connectionFactory.connection();
        List<Request> requestList = new ArrayList<>();

        String querySql = "SELECT * FROM request WHERE customer=?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Request request = new Request();
            request.setDeparture(AddressDao.getInstance().findAddressByAddressId(resultSet.getInt("departure")));
            request.setArrivalDestination(AddressDao.getInstance().findAddressByAddressId(resultSet.getInt("arrival")));
            request.setVehicle(VehicleDao.getInstance().findVehicleByPlate(resultSet.getString("vehicle")));
            request.setWorker(UserDao.getInstance().findWorkerById(resultSet.getInt("worker")));
            request.setCustomer(UserDao.getInstance().findCustomerrById(resultSet.getInt("customer")));
            request.setStatus(resultSet.getInt("status"));
            requestList.add(request);
        }
        return requestList;
    }
}
