package dao;

import connection.ConnectionFactory;
import control.UserController;
import model.Request;
import model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private ConnectionFactory connectionFactory;

    private static VehicleDao instance = new VehicleDao();

    public VehicleDao() {this.connectionFactory = new ConnectionFactory();}

    public void save(Vehicle vehicle) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "INSERT INTO vehicle (brand, model, plate, available, vehicle_size, intercity, km_price, owner_id)" +
                "values (?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1, vehicle.getBrand());
        preparedStatement.setString(2, vehicle.getModel());
        preparedStatement.setString(3, vehicle.getPlate());
        preparedStatement.setBoolean(4, true);
        preparedStatement.setInt(5, vehicle.getVehicleSize().getVehicleSize());
        preparedStatement.setBoolean(6, vehicle.isIntercity());
        preparedStatement.setDouble(7, vehicle.getKmPrice());
        preparedStatement.setInt(8, UserController.getSessionUser().getUserId());
        preparedStatement.execute();

        connection.close();
    }

    public void delete(Vehicle vehicle) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "DELETE FROM vehicle WHERE vehicle_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setInt(1, vehicle.getVehicleId());
        connection.close();
    }

    //TODO DEFINIR COMO SERÁ IMPLEMENTADO
    @Deprecated
    public Vehicle findVehicleByPlate(String plate) throws SQLException {
        //TODO FAZER REQUESTDAO PARA CONSEGUIR LISTA DE REQUEST DO VEÍCULO
        Connection connection = connectionFactory.connection();

        Vehicle vehicle = new Vehicle();
        List<Request> requestList = new ArrayList<>();

        String querySql = "SELECT * FROM vehicle v JOIN request r ON v.plate=r.vehicle WHERE v.plate=?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1,plate);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Request firstRequest = new Request();
            vehicle.setVehicleId(resultSet.getInt("vehicle_id"));
            vehicle.setAvailable(resultSet.getBoolean("available"));
            vehicle.setVehicleSize(resultSet.getInt("vehicle_size"));
            vehicle.setBrand(resultSet.getString("brand"));
            vehicle.setModel(resultSet.getString("model"));
            vehicle.setPlate(resultSet.getString("plate"));
            vehicle.setIntercity(resultSet.getBoolean("intercity"));
            vehicle.setKmPrice(resultSet.getDouble("km_price"));
            firstRequest.setDeparture(AddressDao.getInstance().findAddressByAddressId(resultSet.getInt("departure")));
            firstRequest.setArrivalDestination(AddressDao.getInstance().findAddressByAddressId(resultSet.getInt("arrival")));
            firstRequest.setVehicle(vehicle);
        }
        connection.close();

        return vehicle;
    }

    public void update(Vehicle vehicle) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "UPDATE vehicle SET brand=?, model=?, plate=?, vehicle_size=?, intercity=?, km_price=?" +
                "WHERE vehicle_id=?";


        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1, vehicle.getBrand());
        preparedStatement.setString(2, vehicle.getModel());
        preparedStatement.setString(3, vehicle.getPlate());
        preparedStatement.setInt(4, vehicle.getVehicleSize().getVehicleSize());
        preparedStatement.setBoolean(5, vehicle.isIntercity());
        preparedStatement.setDouble(6, vehicle.getKmPrice());
        preparedStatement.execute();
        connection.close();
    }

    public List<Vehicle> listWorkerVehicles(int id) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        Connection connection = connectionFactory.connection();

        String querySql = "SELECT * FROM vehicle WHERE owner_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setKmPrice(resultSet.getDouble("km_price"));
            vehicle.setIntercity(resultSet.getBoolean("intercity"));
            vehicle.setPlate(resultSet.getString("plate"));
            vehicle.setModel(resultSet.getString("model"));
            vehicle.setBrand(resultSet.getString("brand"));
            vehicle.setVehicleSize(resultSet.getInt("vehicle_size"));
            vehicle.setAvailable(resultSet.getBoolean("available"));
            vehicle.setVehicleId(resultSet.getInt("vehicle_id"));

            vehicles.add(vehicle);
        }
        connection.close();

        return vehicles;
    }

    public static VehicleDao getInstance() {
        return instance;
    }
}
