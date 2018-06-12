package dao;

import connection.ConnectionFactory;
import model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleDao {

    private ConnectionFactory connectionFactory;

    public VehicleDao() {this.connectionFactory = new ConnectionFactory();}

    public void save(Vehicle vehicle) throws SQLException {
        Connection connection = connectionFactory.connection();

        String querySql = "INSERT INTO vehicle (brand, model, plate, available, vehicle_size, intercity, km_price)" +
                "values (?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(querySql);

        preparedStatement.setString(1, vehicle.getBrand());
        preparedStatement.setString(2, vehicle.getModel());
        preparedStatement.setString(3, vehicle.getPlate());
        preparedStatement.setBoolean(4, true);
        preparedStatement.setInt(5, vehicle.getVehicleSize().getVehicleSize());
        preparedStatement.setBoolean(6, vehicle.isIntercity());
        preparedStatement.setDouble(7, vehicle.getKmPrice());
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
}