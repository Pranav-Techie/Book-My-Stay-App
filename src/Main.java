import java.util.*;

// Reservation class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Shared Booking System
class BookingSystem {

    private Queue<Reservation> queue = new LinkedList<>();
    private Map<String, Integer> inventory = new HashMap<>();

    public BookingSystem() {
        inventory.put("Deluxe", 2);
        inventory.put("Standard", 1);
    }

    // Add booking request (thread-safe)
    public synchronized void addRequest(Reservation r) {
        queue.add(r);
        System.out.println("Request Added: " + r.getGuestName());
    }

    // Process booking (critical section)
    public synchronized void processBooking() {

        if (queue.isEmpty()) return;

        Reservation r = queue.poll();

        String type = r.getRoomType();

        if (inventory.getOrDefault(type, 0) > 0) {

            // simulate delay (to expose race condition if not synchronized)
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            inventory.put(type, inventory.get(type) - 1);

            System.out.println("Booking Confirmed: "
                    + r.getGuestName() + " (" + type + ")");

        } else {
            System.out.println("Booking Failed (No Room): "
                    + r.getGuestName());
        }
    }
}

// Thread class
class BookingThread extends Thread {

    private BookingSystem system;

    public BookingThread(BookingSystem system) {
        this.system = system;
    }

    public void run() {
        system.processBooking();
    }
}

// Main class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // Add requests
        system.addRequest(new Reservation("Pranav", "Deluxe"));
        system.addRequest(new Reservation("Rahul", "Deluxe"));
        system.addRequest(new Reservation("Anjali", "Deluxe")); // extra request

        // Create multiple threads
        Thread t1 = new BookingThread(system);
        Thread t2 = new BookingThread(system);
        Thread t3 = new BookingThread(system);

        // Start threads
        t1.start();
        t2.start();
        t3.start();
    }
}