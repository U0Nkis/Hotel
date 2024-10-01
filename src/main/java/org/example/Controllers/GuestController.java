package org.example.Controllers;

import org.example.Entities.Guest;
import org.example.Services.GuestService;

import java.util.Scanner;

public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    public void manageGuests(Scanner scanner) {
        System.out.println("1. Add Guest");
        System.out.println("2. View Guest");
        System.out.println("3. Update Guest");
        System.out.println("4. Delete Guest");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addGuest(scanner);
                break;
            case 2:
                viewGuest(scanner);
                break;
            case 3:
                updateGuest(scanner);
                break;
            case 4:
                deleteGuest(scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void addGuest(Scanner scanner) {
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        Guest guest = new Guest(0, fullName, address, phoneNumber);
        guestService.addGuest(guest);
        System.out.println("Guest added: " + guest);
    }

    private void viewGuest(Scanner scanner) {
        System.out.print("Enter guest ID: ");
        int id = scanner.nextInt();
        Guest guest = guestService.findGuestById(id);
        System.out.println(guest != null ? guest : "Guest not found");
    }

    private void updateGuest(Scanner scanner) {
        System.out.print("Enter guest ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        Guest guest = new Guest(id, fullName, address, phoneNumber);
        guestService.updateGuest(id, guest);
        System.out.println("Guest updated: " + guest);
    }

    private void deleteGuest(Scanner scanner) {
        System.out.print("Enter guest ID: ");
        int id = scanner.nextInt();
        guestService.deleteGuest(id);
        System.out.println("Guest deleted.");
    }
}
