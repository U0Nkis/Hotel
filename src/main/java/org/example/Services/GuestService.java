package org.example.Services;

import org.example.Entities.Guest;
import org.example.Repositories.api.Repository;

import java.util.List;
import java.util.Optional;

public class GuestService {
    private Repository<Guest> guestRepository;

    public GuestService(Repository<Guest> guestRepository) {
        this.guestRepository = guestRepository;
    }

    public void addGuest(Guest guest) {
        guestRepository.save(guest);
    }

    public Optional<Guest> findGuestById(Long id) {
        return guestRepository.findById(id);
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public void removeGuest(Long id) {
        guestRepository.deleteById(id);
    }
}
