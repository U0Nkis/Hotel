package org.example.Services;

import org.example.Entities.Guest;
import org.example.Repositories.GuestRepository;

import java.util.Map;

public class GuestService {
    private final GuestRepository guestRepository = new GuestRepository();

    public Guest addGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public Guest findGuestById(int id) {
        return guestRepository.findById(id);
    }

    public Map<Integer, Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public void updateGuest(int id, Guest guest) {
        guestRepository.save(guest);
    }

    public void deleteGuest(int id) {
        guestRepository.deleteById(id);
    }
}
