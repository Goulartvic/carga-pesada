package model;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    private int vehicleId;

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
        requests = new ArrayList<>();
    }

    public Vehicle() {
        available = true;
        requests = new ArrayList<>();
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

    public void setVehicleSize(int vehicleSize) {
        switch (vehicleSize) {
            case 1:
                this.vehicleSize = VehicleSize.SMALL;
                break;
            case 2:
                this.vehicleSize = VehicleSize.MEDIUM;
                break;
            case 3:
                this.vehicleSize = VehicleSize.BIG;
                break;
        }
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

    public int getVehicleId() { return vehicleId; }

    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public void setVehicleSize(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", plate='" + plate + '\'' +
                ", available=" + available +
                ", vehicleSize=" + vehicleSize +
                ", intercity=" + intercity +
                ", kmPrice=" + kmPrice +
                ", requests=" + requests +
                '}';
    }
}
