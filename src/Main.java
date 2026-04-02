import java.util.*;

//  Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

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

// Validator class
class BookingValidator {

    private static final Set<String> validRoomTypes =
            new HashSet<>(Arrays.asList("Deluxe", "Standard", "Suite"));

    public static void validate(Reservation r, Map<String, Integer> inventory)
            throws InvalidBookingException {

        //  Invalid room type
        if (!validRoomTypes.contains(r.getRoomType())) {
            throw new InvalidBookingException("Invalid Room Type: " + r.getRoomType());
        }

        //  No inventory
        if (inventory.getOrDefault(r.getRoomType(), 0) <= 0) {
            throw new InvalidBookingException(
                    "No rooms available for: " + r.getRoomType());
        }
    }
}

// Main class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        // Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Deluxe", 1);
        inventory.put("Standard", 0); // no rooms
        inventory.put("Suite", 2);

        // Test bookings
        List<Reservation> requests = Arrays.asList(
                new Reservation("Pranav", "Deluxe"),
                new Reservation("Rahul", "Standard"),   //  no inventory
                new Reservation("Anjali", "Luxury")     //  invalid type
        );

        for (Reservation r : requests) {
            try {
                //  Validate before processing
                BookingValidator.validate(r, inventory);

                // If valid → process booking
                inventory.put(r.getRoomType(),
                        inventory.get(r.getRoomType()) - 1);

                System.out.println("Booking Successful: "
                        + r.getGuestName() + " (" + r.getRoomType() + ")");

            } catch (InvalidBookingException e) {
                //  Graceful failure
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }

        System.out.println("\nFinal Inventory: " + inventory);
    }
}