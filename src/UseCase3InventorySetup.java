/**
 * Use Case 3: Centralized Room Inventory Management
 * Version: 3.0
 */

public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("Book My Stay App - Version 3.0");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("\nChecking availability of Single Room:");
        System.out.println(inventory.getAvailability("Single Room"));

        System.out.println("\nUpdating Double Room availability to 3");
        inventory.updateAvailability("Double Room", 3);

        inventory.displayInventory();
    }
}