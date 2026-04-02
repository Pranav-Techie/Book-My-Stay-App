import java.util.*;

// Reservation class (same as UC5)
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

// Main class
public class Main {

    public static void main(String[] args) {

        //  Booking Queue (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Pranav", "Deluxe"));
        bookingQueue.add(new Reservation("Rahul", "Standard"));
        bookingQueue.add(new Reservation("Anjali", "Deluxe"));

        //  Inventory (room count)
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Deluxe", 2);
        inventory.put("Standard", 1);

        //  Track allocated room IDs (no duplicates)
        Set<String> allocatedRoomIds = new HashSet<>();

        //  Map roomType → allocated IDs
        Map<String, Set<String>> roomAllocationMap = new HashMap<>();

        int roomCounter = 1;

        System.out.println("Processing Booking Requests...\n");

        //  Process queue
        while (!bookingQueue.isEmpty()) {

            Reservation request = bookingQueue.poll(); // FIFO
            String type = request.getRoomType();

            // Check availability
            if (inventory.getOrDefault(type, 0) > 0) {

                // Generate unique room ID
                String roomId = type.substring(0, 3).toUpperCase() + "-" + roomCounter++;

                // Ensure uniqueness
                if (!allocatedRoomIds.contains(roomId)) {

                    allocatedRoomIds.add(roomId);

                    // Store in map
                    roomAllocationMap
                            .computeIfAbsent(type, k -> new HashSet<>())
                            .add(roomId);

                    // Decrement inventory
                    inventory.put(type, inventory.get(type) - 1);

                    // Confirm booking
                    System.out.println("Booking Confirmed:");
                    System.out.println("Guest: " + request.getGuestName()
                            + ", Room Type: " + type
                            + ", Room ID: " + roomId + "\n");
                }

            } else {
                System.out.println("Booking Failed (No Rooms Available): "
                        + request.getGuestName() + " (" + type + ")\n");
            }
        }

        // Final state
        System.out.println("Final Inventory: " + inventory);
        System.out.println("Allocated Room IDs: " + allocatedRoomIds);
    }
}