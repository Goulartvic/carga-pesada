package control;

import dao.VehicleDao;
import model.Request;
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

    public void addRequestInVehicle(Request request, Vehicle vehicle, Worker worker) {
//        TODO - fazer tratamento de exceções
//        TODO - persistir vehicle
        vehicle.getRequests().add(request);
        update(worker, vehicle);
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

    public static VehicleController getInstance() {
        return instance;
    }
}
