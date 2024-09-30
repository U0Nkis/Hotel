package org.example.Services;

import org.example.Entities.Booking;
import org.example.Entities.Guest;
import org.example.Entities.Room;
import org.example.Repositories.BookingRepository;

import java.util.Map;

public class BookingService {
    private final BookingRepository bookingRepository = new BookingRepository();
    private final GuestService guestService = new GuestService();
    private final RoomService roomService = new RoomService();

    public Booking createBooking(int guestId, int roomId, String startDate, String endDate) {
        Guest guest = guestService.findGuestById(guestId);
        Room room = roomService.findRoomById(roomId);
        Booking booking = new Booking(0, guest, room, startDate, endDate);
        return bookingRepository.save(booking);
    }

    public Booking findBookingById(int id) {
        return bookingRepository.findById(id);
    }

    public Map<Integer, Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void updateBooking(int id, Booking booking) {
        bookingRepository.save(booking);
    }

    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }
}
