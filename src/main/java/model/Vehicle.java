package model;

public class Vehicle {

    private String brand;

    private String model;

    private String plate;

    private boolean available;

    private VehicleSize vehicleSize;

    private boolean intercity;

    public Vehicle(String brand, String model, String plate, VehicleSize vehicleSize, boolean intercity) {
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.available = true;
        this.vehicleSize = vehicleSize;
        this.intercity = intercity;
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
}
