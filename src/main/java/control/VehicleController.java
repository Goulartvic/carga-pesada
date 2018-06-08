package control;

import dao.VehicleDao;
import model.Vehicle;
import model.Worker;

import java.sql.SQLException;

public class VehicleController {

    private VehicleDao vehicleDao;

    public void update(Worker worker, Vehicle vehicle) {
        vehicleDao = new VehicleDao();

        int vehicleIndex = worker.getVehicles().indexOf(vehicle);
        Vehicle vehicleToSave = worker.getVehicles().get(vehicleIndex);

        try {
            vehicleDao.update(vehicleToSave);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
