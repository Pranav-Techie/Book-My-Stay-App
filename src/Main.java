<<<<<<< HEAD
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
=======
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    IO.println(String.format("Hello and welcome!"));

    for (int i = 1; i <= 5; i++) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        IO.println("i = " + i);
    }
}
>>>>>>> d24eafb69947e3736c3c6117673a660852f57e98
