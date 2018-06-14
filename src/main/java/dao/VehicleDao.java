package dao;

import connection.ConnectionFactory;
import control.UserController;
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

        String querySql = "DELETE FROM vehicle where vehicle_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setInt(1, vehicle.getVehicleId());
        connection.close();
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
