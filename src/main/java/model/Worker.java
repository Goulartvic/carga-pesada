package model;

import java.util.List;

public class Worker extends User {

    private String cpf;

    private String phoneNumber;

    private boolean intercity;

    private Address address;

    private List<Vehicle> vehicle;

    public Worker(int userId, String username, String password, String name, String cpf, String phoneNumber, boolean intercity, Address address) {
        super(userId, username, password, name, UserType.WORKER);
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.intercity = intercity;
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

    public boolean isIntercity() {
        return intercity;
    }

    public void setIntercity(boolean intercity) {
        this.intercity = intercity;
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
}
