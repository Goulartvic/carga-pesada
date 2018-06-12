package control;

import dao.VehicleDao;
import model.Vehicle;
import model.Worker;

import java.sql.SQLException;

public class VehicleController {

    private static VehicleController instance = new VehicleController();

    public void update(Worker worker, Vehicle vehicle) {

        int vehicleIndex = worker.getVehicles().indexOf(vehicle);
        Vehicle vehicleToSave = worker.getVehicles().get(vehicleIndex);

        try {
            VehicleDao.getInstance().update(vehicleToSave);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(String brand, String model, String plate, boolean intercity, int size, String  kmPrice) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setPlate(plate);
        vehicle.setIntercity(intercity);
        vehicle.setVehicleSize(size);
        vehicle.setKmPrice(Double.parseDouble(kmPrice));
        ((Worker) UserController.getSessionUser()).getVehicles().add(vehicle);

        try {
            VehicleDao.getInstance().save(vehicle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static VehicleController getInstance() {
        return instance;
    }
}
