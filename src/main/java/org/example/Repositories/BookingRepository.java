package org.example.Repositories;

import org.example.Entities.Booking;

import java.util.HashMap;
import java.util.Map;

public class BookingRepository {
    private final Map<Integer, Booking> bookingStorage = new HashMap<>();
    private int nextId = 1;

    public Booking save(Booking booking) {
        if (booking.getId() == 0) {
            booking = new Booking(nextId++, booking.getGuest(), booking.getRoom(), booking.getStartDate(), booking.getEndDate());
        }
        bookingStorage.put(booking.getId(), booking);
        return booking;
    }

    public Booking findById(int id) {
        return bookingStorage.get(id);
    }

    public Map<Integer, Booking> findAll() {
        return bookingStorage;
    }

    public void deleteById(int id) {
        bookingStorage.remove(id);
    }
}
