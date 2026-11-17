package parking.service;

import parking.exceptions.*;
import parking.models.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingManager {
    private List<ParkingSlot> slots = new ArrayList<>();

    public ParkingManager(int twoWheelerCount, int fourWheelerCount) {
        int id = 1;
        for (int i = 0; i < twoWheelerCount; i++) {
            slots.add(new ParkingSlot(id++, "2-Wheeler"));
        }
        for (int i = 0; i < fourWheelerCount; i++) {
            slots.add(new ParkingSlot(id++, "4-Wheeler"));
        }
    }

    // Synchronized method (Module 5) prevents double-booking of the same slot
    public synchronized int parkVehicle(Vehicle vehicle) throws ParkingFullException {
        for (ParkingSlot slot : slots) {
            if (slot.getStatus() == SlotStatus.AVAILABLE &&
                slot.getAllowedCategory().equals(vehicle.getVehicleCategory())) {
                slot.assignVehicle(vehicle);
                return slot.getSlotId();
            }
        }
        throw new ParkingFullException("No " + vehicle.getVehicleCategory() + " slots available!");
    }

    public synchronized void exitVehicle(int slotId) throws InvalidSlotException {
        for (ParkingSlot slot : slots) {
            if (slot.getSlotId() == slotId) {
                if (slot.getStatus() == SlotStatus.AVAILABLE) {
                    throw new InvalidSlotException("Slot #" + slotId + " is already empty!");
                }

                Vehicle v = slot.getParkedVehicle();
                long elapsedMillis = System.currentTimeMillis() - v.getEntryTimeMillis();
                
                // Demo Trick: 10 seconds real time = 1 simulated hour
                long simulatedHours = Math.max(1, elapsedMillis / 10000);
                double fee = v.calculateFee(simulatedHours); // Polymorphic call

                System.out.println("\n--- PARKING RECEIPT ---");
                System.out.println("Vehicle No : " + v.getVehicleNumber());
                System.out.println("Category   : " + v.getVehicleCategory());
                System.out.println("Slot Freed : #" + slotId);
                System.out.println("Duration   : " + simulatedHours + " hr(s) [Demo: 10s = 1hr]");
                System.out.println("Total Fee  : Rs. " + fee);
                System.out.println("-----------------------\n");

                slot.removeVehicle();
                return;
            }
        }
        throw new InvalidSlotException("Slot #" + slotId + " does not exist!");
    }

    public void displayStatus() {
        System.out.println("\n=== LIVE PARKING LOT STATUS ===");
        for (ParkingSlot slot : slots) {
            String info = (slot.getStatus() == SlotStatus.OCCUPIED)
                    ? "OCCUPIED [" + slot.getParkedVehicle().getVehicleNumber() + "]"
                    : "AVAILABLE";
            System.out.printf("Slot #%d (%s) : %s%n",
                    slot.getSlotId(), slot.getAllowedCategory(), info);
        }
        System.out.println("===============================\n");
    }
}
