package control;

import dao.VehicleDao;
import model.Request;
import model.Vehicle;
import model.Worker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleController {

    private static VehicleController instance = new VehicleController();

    public void update(Vehicle vehicle) {

//        int vehicleIndex = worker.getVehicles().indexOf(vehicle);
//        Vehicle vehicleToSave = worker.getVehicles().get(vehicleIndex);

        try {
            VehicleDao.getInstance().update(vehicle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(String brand, String model, String plate, boolean intercity, int size, String kmPrice) {
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

    public void deleteVehicle(Vehicle vehicle) {
        Worker worker = (Worker) UserController.getSessionUser();
        worker.getVehicles().remove(vehicle);
        UserController.setSessionUser(worker);
        try {
            VehicleDao.getInstance().delete(vehicle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vehicle findVehicleByPlate(String vehiclePlate) {
        Vehicle vehicle = new Vehicle();
        try {
            vehicle = VehicleDao.getInstance().findVehicleByPlate(vehiclePlate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    public List<Vehicle> listWorkerVehicles(int id) {
        List<Vehicle> vehicleList = new ArrayList<>();

        try {
            vehicleList = VehicleDao.getInstance().listWorkerAvailableVehicles(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleList;
    }

    public static VehicleController getInstance() {
        if (instance == null) {
            instance = new VehicleController();
        }
        return instance;
    }
}
