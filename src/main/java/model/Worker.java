package model;

import java.util.List;

public class Worker extends User {

    private String cpf;

    private String phoneNumber;

    private Address address;

    private List<Vehicle> vehicle;

    private int rating;

    public Worker(int userId, String username, String password, String name, String cpf, String phoneNumber, Address address) {
        super(userId, username, password, name, UserType.WORKER);
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
