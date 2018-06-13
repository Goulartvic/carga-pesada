package model;

import java.util.ArrayList;
import java.util.List;

public class  Worker extends User {

    private int rating;

    private List<Vehicle> vehicles;

    public Worker(Address address, String cpf, String name, String password, String phoneNumber, int userId, String username) {
        super(address, cpf, name, password, phoneNumber, userId, username, UserType.WORKER);
        this.vehicles = new ArrayList<>();
    }

    public Worker(Worker worker) {
        super(worker.getAddress(), worker.getCpf(), worker.getName(), worker.getPassword(), worker.getPhoneNumber(), worker.getUserId(), worker.getUsername(), worker.getUserType());
        this.vehicles = worker.getVehicles();
        this.rating = worker.getRating();
    }

    public Worker() {}

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
