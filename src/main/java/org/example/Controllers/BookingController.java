package org.example.Controllers;

import org.example.Entities.Booking;
import org.example.Services.BookingService;
import org.example.Services.GuestService;
import org.example.Services.RoomService;

import java.util.Scanner;

public class BookingController {
    private final BookingService bookingService;
    private final GuestService guestService;
    private final RoomService roomService;

    public BookingController(BookingService bookingService, GuestService guestService, RoomService roomService) {
        this.bookingService = bookingService;
        this.guestService = guestService;
        this.roomService = roomService;
    }

    public void manageBookings(Scanner scanner) {
        System.out.println("1. Add Booking");
        System.out.println("2. View Booking");
        System.out.println("3. Update Booking");
        System.out.println("4. Delete Booking");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addBooking(scanner);
                break;
            case 2:
                viewBooking(scanner);
                break;
            case 3:
                updateBooking(scanner);
                break;
            case 4:
                deleteBooking(scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void addBooking(Scanner scanner) {
        System.out.print("Enter guest ID: ");
        int guestId = scanner.nextInt();
        System.out.print("Enter room ID: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        Booking booking = bookingService.createBooking(guestId, roomId, startDate, endDate);
        System.out.println("Booking created: " + booking);
    }

    private void viewBooking(Scanner scanner) {
        System.out.print("Enter booking ID: ");
        int id = scanner.nextInt();
        Booking booking = bookingService.findBookingById(id);
        System.out.println(booking != null ? booking : "Booking not found");
    }

    private void updateBooking(Scanner scanner) {
        System.out.print("Enter booking ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter guest ID: ");
        int guestId = scanner.nextInt();
        System.out.print("Enter room ID: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        Booking booking = new Booking(id, guestService.findGuestById(guestId), roomService.findRoomById(roomId), startDate, endDate);
        bookingService.updateBooking(id, booking);
        System.out.println("Booking updated: " + booking);
    }

    private void deleteBooking(Scanner scanner) {
        System.out.print("Enter booking ID: ");
        int id = scanner.nextInt();
        bookingService.deleteBooking(id);
        System.out.println("Booking deleted.");
    }
}
