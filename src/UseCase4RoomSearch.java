/**
 * Use Case 4: Room Search & Availability Check
 * Version: 4.0
 */

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("Book My Stay App - Version 4.0");

        RoomInventory inventory = new RoomInventory();

        RoomSearchService searchService = new RoomSearchService(inventory);

        searchService.searchAvailableRooms();
    }
}