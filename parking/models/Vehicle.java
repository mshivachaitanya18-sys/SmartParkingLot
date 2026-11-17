package parking.models;

public abstract class Vehicle implements Parkable {
    private String vehicleNumber;
    private long entryTimeMillis;

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber.toUpperCase();
        this.entryTimeMillis = System.currentTimeMillis();
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public long getEntryTimeMillis() {
        return entryTimeMillis;
    }
}
