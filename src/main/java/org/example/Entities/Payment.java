package org.example.Entities;

public class Payment {
    private int id;
    private Invoice invoice;
    private String paymentMethod;
    private boolean isPaid;

    public Payment(int id, Invoice invoice, String paymentMethod, boolean isPaid) {
        this.id = id;
        this.invoice = invoice;
        this.paymentMethod = paymentMethod;
        this.isPaid = isPaid;
    }

    public int getId() {
        return id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", invoice=" + invoice +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", isPaid=" + isPaid +
                '}';
    }
}
