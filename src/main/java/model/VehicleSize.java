package model;

public enum VehicleSize {
    SMALL(1), MEDIUM(2), BIG(3);

    private final int vehicleSize;

    VehicleSize(int vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public int getVehicleSize() {
        return vehicleSize;
    }
}
