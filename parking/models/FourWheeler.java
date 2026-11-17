package parking.models;

public class FourWheeler extends Vehicle {
    private static final double HOURLY_RATE = 50.0; // ₹50 per hour

    public FourWheeler(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public double calculateFee(long hoursParked) {
        return Math.max(1, hoursParked) * HOURLY_RATE;
    }

    @Override
    public String getVehicleCategory() {
        return "4-Wheeler";
    }
}
