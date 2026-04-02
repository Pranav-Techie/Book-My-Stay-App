import java.util.*;

// Reservation class
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room: " + roomType;
    }
}

// Booking History Service
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    // Add confirmed booking
    public void addReservation(Reservation r) {
        history.add(r);
    }

    // Get all bookings
    public List<Reservation> getAllReservations() {
        return history;
    }
}

// Reporting Service
class BookingReportService {

    public static void generateReport(List<Reservation> reservations) {

        System.out.println("\n===== BOOKING HISTORY REPORT =====");

        for (Reservation r : reservations) {
            System.out.println(r);
        }

        System.out.println("\nTotal Bookings: " + reservations.size());

        // Count per room type
        Map<String, Integer> roomCount = new HashMap<>();

        for (Reservation r : reservations) {
            roomCount.put(
                    r.getRoomType(),
                    roomCount.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        System.out.println("\nBookings by Room Type:");
        for (String type : roomCount.keySet()) {
            System.out.println(type + ": " + roomCount.get(type));
        }
    }
}

// Main class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from UC6)
        history.addReservation(new Reservation("RES-101", "Pranav", "Deluxe"));
        history.addReservation(new Reservation("RES-102", "Rahul", "Standard"));
        history.addReservation(new Reservation("RES-103", "Anjali", "Deluxe"));

        // Admin views report
        BookingReportService.generateReport(history.getAllReservations());
    }
}