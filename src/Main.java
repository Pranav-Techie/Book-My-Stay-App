import java.util.*;

// Reservation class
class Reservation {
    private String reservationId;
    private String roomType;
    private String roomId;

    public Reservation(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }
}

// Cancellation Service
class CancellationService {

    public static void cancelBooking(
            String reservationId,
            Map<String, Reservation> bookings,
            Map<String, Integer> inventory,
            Stack<String> rollbackStack
    ) {

        //  Validate existence
        if (!bookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Reservation not found -> " + reservationId);
            return;
        }

        Reservation r = bookings.get(reservationId);

        //  Push to rollback stack (LIFO)
        rollbackStack.push(r.getRoomId());

        //  Restore inventory
        inventory.put(
                r.getRoomType(),
                inventory.getOrDefault(r.getRoomType(), 0) + 1
        );

        //  Remove booking (mark cancelled)
        bookings.remove(reservationId);

        System.out.println("Cancellation Successful: " + reservationId);
    }
}

// Main class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        //  Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Deluxe", 0);
        inventory.put("Standard", 1);

        //  Existing bookings (from UC6)
        Map<String, Reservation> bookings = new HashMap<>();

        bookings.put("RES-101", new Reservation("RES-101", "Deluxe", "DEL-1"));
        bookings.put("RES-102", new Reservation("RES-102", "Standard", "STA-2"));

        //  Stack for rollback
        Stack<String> rollbackStack = new Stack<>();

        // 🔹 Perform cancellations
        CancellationService.cancelBooking("RES-101", bookings, inventory, rollbackStack);

        //  Try invalid cancellation
        CancellationService.cancelBooking("RES-999", bookings, inventory, rollbackStack);

        //  Try duplicate cancellation
        CancellationService.cancelBooking("RES-101", bookings, inventory, rollbackStack);

        // Final state
        System.out.println("\nFinal Inventory: " + inventory);
        System.out.println("Remaining Bookings: " + bookings.keySet());
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}