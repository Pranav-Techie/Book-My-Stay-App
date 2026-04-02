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

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

// Main class
public class Main {

    public static void main(String[] args) {

        // ✅ Queue for booking requests
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Simulate booking requests
        bookingQueue.add(new Reservation("Pranav", "Deluxe"));
        bookingQueue.add(new Reservation("Rahul", "Standard"));
        bookingQueue.add(new Reservation("Anjali", "Suite"));

        // Display queue (FIFO order)
        System.out.println("Booking Requests in Queue (FIFO Order):");

        for (Reservation r : bookingQueue) {
            System.out.println(r);
        }

        // Peek next request (no removal)
        System.out.println("\nNext Request to Process:");
        System.out.println(bookingQueue.peek());
    }
}