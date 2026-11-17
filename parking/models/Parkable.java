package parking.models;

public interface Parkable {
    double calculateFee(long hoursParked);
    String getVehicleCategory();
}
