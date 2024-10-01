package org.example;

import java.util.Scanner;

import org.example.Services.*;
import org.example.Controllers.*;

public class HotelApp {
    private final GuestService guestService = new GuestService();
    private final RoomService roomService = new RoomService();
    private final BookingService bookingService = new BookingService();
    private final ServiceService serviceService = new ServiceService();
    private final MealService mealService = new MealService();
    private final InvoiceService invoiceService = new InvoiceService();
    private final PaymentService paymentService = new PaymentService();
    private final ReviewService reviewService = new ReviewService();

    private final GuestController guestController = new GuestController(guestService);
    private final RoomController roomController = new RoomController(roomService);
    private final BookingController bookingController = new BookingController(bookingService, guestService, roomService);
    private final ServiceController serviceController = new ServiceController(serviceService);
    private final MealController mealController = new MealController(mealService);
    private final InvoiceController invoiceController = new InvoiceController(invoiceService, bookingService);
    private final PaymentController paymentController = new PaymentController(paymentService, invoiceService);
    private final ReviewController reviewController = new ReviewController(reviewService, guestService);

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Hotel Management System ---");
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
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    guestController.manageGuests(scanner);
                    break;
                case 2:
                    roomController.manageRooms(scanner);
                    break;
                case 3:
                    bookingController.manageBookings(scanner);
                    break;
                case 4:
                    serviceController.manageServices(scanner);
                    break;
                case 5:
                    mealController.manageMeals(scanner);
                    break;
                case 6:
                    invoiceController.manageInvoices(scanner);
                    break;
                case 7:
                    paymentController.managePayments(scanner);
                    break;
                case 8:
                    reviewController.manageReviews(scanner);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        HotelApp app = new HotelApp();
        app.start();
    }
}
