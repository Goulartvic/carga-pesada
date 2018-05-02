package model;

import java.util.List;

public class Vehicle {

    private String brand;

    private String model;

    private String plate;

    private boolean available;

    private VehicleSize vehicleSize;

    private boolean intercity;

    private double kmPrice;

    private List<Request> requests;

    public Vehicle(String brand, String model, String plate, VehicleSize vehicleSize, boolean intercity, double kmPrice) {
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.available = true;
        this.vehicleSize = vehicleSize;
        this.intercity = intercity;
        this.kmPrice = kmPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public boolean isIntercity() {
        return intercity;
    }

    public void setIntercity(boolean intercity) {
        this.intercity = intercity;
    }

    public double getKmPrice() {
        return kmPrice;
    }

    public void setKmPrice(double kmPrice) {
        this.kmPrice = kmPrice;
    }
}
