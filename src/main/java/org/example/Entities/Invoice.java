package org.example.Entities;

import java.util.List;

public class Invoice {
    private int id;
    private Booking booking;
    private List<Service> services;
    private double totalAmount;

    public Invoice(int id, Booking booking, List<Service> services) {
        this.id = id;
        this.booking = booking;
        this.services = services;
        this.totalAmount = calculateTotalAmount();
    }

    private double calculateTotalAmount() {
        double sum = booking.getRoom().getPricePerNight();
        for (Service service : services) {
            sum += service.getPrice();
        }
        return sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Invoice{id=" + id + ", booking=" + booking + ", totalAmount=" + totalAmount + "}";
    }
}
