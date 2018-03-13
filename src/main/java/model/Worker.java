package model;

public class Worker extends User {

    private String cpf;

    private String phoneNumber;

    private boolean intercity;

    private boolean available;

    private Address address;

    private Vehicle vehicle;

    public Worker(String username, String password, String name, String cpf, String phoneNumber, boolean intercity, Address address) {
        super(username, password, name);
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.intercity = intercity;
        this.address = address;
        this.available = true;
    }

    public String getCpf() {
        return cpf;
    }

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
