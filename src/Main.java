import java.io.*;
import java.util.*;

// Reservation class (Serializable)
class Reservation implements Serializable {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return reservationId + " | " + guestName + " | " + roomType;
    }
}

// Wrapper class to store system state
class SystemState implements Serializable {
    Map<String, Integer> inventory;
    List<Reservation> bookings;

    public SystemState(Map<String, Integer> inventory, List<Reservation> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.dat";

    // Save data
    public static void save(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("✅ System state saved successfully.");

        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }

    // Load data
    public static SystemState load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("✅ System state loaded successfully.");
            return (SystemState) ois.readObject();

        } catch (Exception e) {
            System.out.println("⚠ No previous data found. Starting fresh.");
            return null;
        }
    }
}

// Main class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        // Try loading previous state
        SystemState state = PersistenceService.load();

        Map<String, Integer> inventory;
        List<Reservation> bookings;

        if (state != null) {
            // Restore state
            inventory = state.inventory;
            bookings = state.bookings;

            System.out.println("\nRecovered Inventory: " + inventory);
            System.out.println("Recovered Bookings: " + bookings);

        } else {
            // Fresh start
            inventory = new HashMap<>();
            inventory.put("Deluxe", 2);
            inventory.put("Standard", 1);

            bookings = new ArrayList<>();
            bookings.add(new Reservation("RES-101", "Pranav", "Deluxe"));
            bookings.add(new Reservation("RES-102", "Rahul", "Standard"));

            System.out.println("\nNew System Started");
        }

        // Save state before exit
        SystemState newState = new SystemState(inventory, bookings);
        PersistenceService.save(newState);
    }
}