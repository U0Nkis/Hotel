package org.example.Services;

import org.example.Entities.Booking;
import org.example.Entities.Invoice;
import org.example.Entities.Service;
import org.example.Repositories.InvoiceRepository;

import java.util.Map;
import java.util.List;

public class InvoiceService {
    private final InvoiceRepository invoiceRepository = new InvoiceRepository();
    private final BookingService bookingService = new BookingService();

    public Invoice createInvoice(int bookingId, List<Service> services) {
        Booking booking = bookingService.findBookingById(bookingId);
        Invoice invoice = new Invoice(0, booking, services);
        return invoiceRepository.save(invoice);
    }

    public Invoice findInvoiceById(int id) {
        return invoiceRepository.findById(id);
    }

    public Map<Integer, Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public void updateInvoice(int id, Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    public void deleteInvoice(int id) {
        invoiceRepository.deleteById(id);
    }
}
