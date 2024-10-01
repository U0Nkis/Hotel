package org.example.Controllers;

import org.example.Entities.Invoice;
import org.example.Services.BookingService;
import org.example.Services.InvoiceService;

import java.util.List;
import java.util.Scanner;

public class InvoiceController {
    private final InvoiceService invoiceService;
    private final BookingService bookingService;

    public InvoiceController(InvoiceService invoiceService, BookingService bookingService) {
        this.invoiceService = invoiceService;
        this.bookingService = bookingService;
    }

    public void manageInvoices(Scanner scanner) {
        System.out.println("1. Add Invoice");
        System.out.println("2. View Invoice");
        System.out.println("3. Update Invoice");
        System.out.println("4. Delete Invoice");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addInvoice(scanner);
                break;
            case 2:
                viewInvoice(scanner);
                break;
            case 3:
                updateInvoice(scanner);
                break;
            case 4:
                deleteInvoice(scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void addInvoice(Scanner scanner) {
        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();
        // В этой реализации для простоты пока не добавляем услуги
        Invoice invoice = invoiceService.createInvoice(bookingId, List.of());
        System.out.println("Invoice created: " + invoice);
    }

    private void viewInvoice(Scanner scanner) {
        System.out.print("Enter invoice ID: ");
        int id = scanner.nextInt();
        Invoice invoice = invoiceService.findInvoiceById(id);
        System.out.println(invoice != null ? invoice : "Invoice not found");
    }

    private void updateInvoice(Scanner scanner) {
        System.out.print("Enter invoice ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();
        Invoice invoice = new Invoice(id, bookingService.findBookingById(bookingId), List.of());
        invoiceService.updateInvoice(id, invoice);
        System.out.println("Invoice updated: " + invoice);
    }

    private void deleteInvoice(Scanner scanner) {
        System.out.print("Enter invoice ID: ");
        int id = scanner.nextInt();
        invoiceService.deleteInvoice(id);
        System.out.println("Invoice deleted.");
    }
}
