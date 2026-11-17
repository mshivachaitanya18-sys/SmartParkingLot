package parking.models;

public class TwoWheeler extends Vehicle {
    private static final double HOURLY_RATE = 20.0; // ₹20 per hour

    public TwoWheeler(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public double calculateFee(long hoursParked) {
        return Math.max(1, hoursParked) * HOURLY_RATE;
    }

    @Override
    public String getVehicleCategory() {
        return "2-Wheeler";
    }
}
