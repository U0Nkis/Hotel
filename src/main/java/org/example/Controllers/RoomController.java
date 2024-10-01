package org.example.Controllers;

import org.example.Entities.Room;
import org.example.Services.RoomService;

import java.util.Scanner;

public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public void manageRooms(Scanner scanner) {
        System.out.println("1. Add Room");
        System.out.println("2. View Room");
        System.out.println("3. Update Room");
        System.out.println("4. Delete Room");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addRoom(scanner);
                break;
            case 2:
                viewRoom(scanner);
                break;
            case 3:
                updateRoom(scanner);
                break;
            case 4:
                deleteRoom(scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void addRoom(Scanner scanner) {
        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();
        System.out.print("Enter room type: ");
        String type = scanner.nextLine();
        System.out.print("Enter price per night: ");
        double price = scanner.nextDouble();
        System.out.print("Is the room available (true/false): ");
        boolean isAvailable = scanner.nextBoolean();

        Room room = new Room(0, roomNumber, type, price, isAvailable);
        roomService.addRoom(room);
        System.out.println("Room added: " + room);
    }

    private void viewRoom(Scanner scanner) {
        System.out.print("Enter room ID: ");
        int id = scanner.nextInt();
        Room room = roomService.findRoomById(id);
        System.out.println(room != null ? room : "Room not found");
    }

    private void updateRoom(Scanner scanner) {
        System.out.print("Enter room ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();
        System.out.print("Enter room type: ");
        String type = scanner.nextLine();
        System.out.print("Enter price per night: ");
        double price = scanner.nextDouble();
        System.out.print("Is the room available (true/false): ");
        boolean isAvailable = scanner.nextBoolean();

        Room room = new Room(id, roomNumber, type, price, isAvailable);
        roomService.updateRoom(id, room);
        System.out.println("Room updated: " + room);
    }

    private void deleteRoom(Scanner scanner) {
        System.out.print("Enter room ID: ");
        int id = scanner.nextInt();
        roomService.deleteRoom(id);
        System.out.println("Room deleted.");
    }
}
