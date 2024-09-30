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

    public Booking getBooking() {
        return booking;
    }

    public List<Service> getServices() {
        return services;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", booking=" + booking +
                ", services=" + services +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
