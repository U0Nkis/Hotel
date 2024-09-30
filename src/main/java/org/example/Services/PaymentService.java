package org.example.Services;

import org.example.Entities.Invoice;
import org.example.Entities.Payment;
import org.example.Repositories.PaymentRepository;

import java.util.Map;

public class PaymentService {
    private final PaymentRepository paymentRepository = new PaymentRepository();
    private final InvoiceService invoiceService = new InvoiceService();

    public Payment processPayment(int invoiceId, String method) {
        Invoice invoice = invoiceService.findInvoiceById(invoiceId);
        Payment payment = new Payment(0, invoice, method, true);
        return paymentRepository.save(payment);
    }

    public Payment findPaymentById(int id) {
        return paymentRepository.findById(id);
    }

    public Map<Integer, Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void updatePayment(int id, Payment payment) {
        paymentRepository.save(payment);
    }

    public void deletePayment(int id) {
        paymentRepository.deleteById(id);
    }
}
