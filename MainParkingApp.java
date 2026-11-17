import parking.exceptions.*;
import parking.models.*;
import parking.service.ParkingManager;
import java.util.Scanner;

public class MainParkingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Initialize 3 Two-Wheeler slots and 3 Four-Wheeler slots
        ParkingManager manager = new ParkingManager(3, 3);

        while (true) {
            System.out.println("1. Park 2-Wheeler");
            System.out.println("2. Park 4-Wheeler");
            System.out.println("3. Exit Vehicle & Generate Bill");
            System.out.println("4. View Live Slot Status");
            System.out.println("5. Exit System");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Bike Number (e.g., KA-05-XY-9876): ");
                        String bikeNo = sc.nextLine();
                        int bikeSlot = manager.parkVehicle(new TwoWheeler(bikeNo));
                        System.out.println("SUCCESS: Parked at Slot #" + bikeSlot + "\n");
                        break;

                    case 2:
                        System.out.print("Enter Car Number (e.g., KA-01-AB-1234): ");
                        String carNo = sc.nextLine();
                        int carSlot = manager.parkVehicle(new FourWheeler(carNo));
                        System.out.println("SUCCESS: Parked at Slot #" + carSlot + "\n");
                        break;

                    case 3:
                        System.out.print("Enter Slot Number to Vacate: ");
                        int slotId = sc.nextInt();
                        manager.exitVehicle(slotId);
                        break;

                    case 4:
                        manager.displayStatus();
                        break;

                    case 5:
                        System.out.println("Shutting down Smart Parking System...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid option. Try again.\n");
                }
            } catch (ParkingFullException | InvalidSlotException e) {
                System.out.println("ALERT: " + e.getMessage() + "\n");
            }
        }
    }
}
