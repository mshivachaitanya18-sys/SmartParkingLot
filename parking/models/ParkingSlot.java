package parking.models;

public class ParkingSlot {
    private int slotId;
    private String allowedCategory;
    private SlotStatus status;
    private Vehicle parkedVehicle;

    public ParkingSlot(int slotId, String allowedCategory) {
        this.slotId = slotId;
        this.allowedCategory = allowedCategory;
        this.status = SlotStatus.AVAILABLE;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.status = SlotStatus.OCCUPIED;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.status = SlotStatus.AVAILABLE;
    }

    public int getSlotId() { return slotId; }
    public String getAllowedCategory() { return allowedCategory; }
    public SlotStatus getStatus() { return status; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }
}
