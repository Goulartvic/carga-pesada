package model;

import java.util.List;

public class Worker extends User {

    private List<Vehicle> vehicle;

    private int rating;

    public Worker(int userId, String username, String password, String name, String cpf, String phoneNumber, Address address) {
        super(userId, username, password, name, UserType.WORKER, cpf, phoneNumber, address);
    }

    public List<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
