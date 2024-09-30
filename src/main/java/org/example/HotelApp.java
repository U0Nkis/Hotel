package org.example;

import org.example.Entities.Guest;
import org.example.Services.*;

import java.util.*;

public class HotelApp {
    private final GuestService guestService = new GuestService();
//    private final RoomService roomService = new RoomService();
//    private final BookingService bookingService = new BookingService();
//    private final ServiceService serviceService = new ServiceService();
//    private final MealService mealService = new MealService();
//    private final InvoiceService invoiceService = new InvoiceService();
//    private final PaymentService paymentService = new PaymentService();
//    private final ReviewService reviewService = new ReviewService();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Manage Guests");
            System.out.println("2. Manage Rooms");
            System.out.println("3. Manage Bookings");
            System.out.println("4. Manage Services");
            System.out.println("5. Manage Meals");
            System.out.println("6. Manage Invoices");
            System.out.println("7. Manage Payments");
            System.out.println("8. Manage Reviews");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageGuests(scanner);
                    break;
//                case 2:
//                    manageRooms(scanner);
//                    break;
//                case 3:
//                    manageBookings(scanner);
//                    break;
//                case 4:
//                    manageServices(scanner);
//                    break;
//                case 5:
//                    manageMeals(scanner);
//                    break;
//                case 6:
//                    manageInvoices(scanner);
//                    break;
//                case 7:
//                    managePayments(scanner);
//                    break;
//                case 8:
//                    manageReviews(scanner);
//                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void manageGuests(Scanner scanner) {
        System.out.println("1. Add Guest");
        System.out.println("2. View Guest");
        System.out.println("3. Update Guest");
        System.out.println("4. Delete Guest");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

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

    public static void main(String[] args) {
        HotelApp app = new HotelApp();
        app.start();
    }
}
