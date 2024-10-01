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

    public void setId(int id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "Payment{id=" + id + ", invoice=" + invoice + ", paymentMethod='" + paymentMethod + "', isPaid=" + isPaid + "}";
    }
}
