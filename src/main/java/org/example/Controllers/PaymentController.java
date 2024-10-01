package org.example.Controllers;

import org.example.Entities.Payment;
import org.example.Services.InvoiceService;
import org.example.Services.PaymentService;

import java.util.Scanner;

public class PaymentController {
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;

    public PaymentController(PaymentService paymentService, InvoiceService invoiceService) {
        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
    }

    public void managePayments(Scanner scanner) {
        System.out.println("1. Process Payment");
        System.out.println("2. View Payment");
        System.out.println("3. Update Payment");
        System.out.println("4. Delete Payment");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                processPayment(scanner);
                break;
            case 2:
                viewPayment(scanner);
                break;
            case 3:
                updatePayment(scanner);
                break;
            case 4:
                deletePayment(scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void processPayment(Scanner scanner) {
        System.out.print("Enter invoice ID: ");
        int invoiceId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter payment method: ");
        String method = scanner.nextLine();

        Payment payment = paymentService.processPayment(invoiceId, method);
        System.out.println("Payment processed: " + payment);
    }

    private void viewPayment(Scanner scanner) {
        System.out.print("Enter payment ID: ");
        int id = scanner.nextInt();
        Payment payment = paymentService.findPaymentById(id);
        System.out.println(payment != null ? payment : "Payment not found");
    }

    private void updatePayment(Scanner scanner) {
        System.out.print("Enter payment ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter payment method: ");
        String method = scanner.nextLine();

        Payment payment = new Payment(id, invoiceService.findInvoiceById(id), method, true);
        paymentService.updatePayment(id, payment);
        System.out.println("Payment updated: " + payment);
    }

    private void deletePayment(Scanner scanner) {
        System.out.print("Enter payment ID: ");
        int id = scanner.nextInt();
        paymentService.deletePayment(id);
        System.out.println("Payment deleted.");
    }
}
