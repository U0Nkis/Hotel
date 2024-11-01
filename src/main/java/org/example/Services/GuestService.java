package org.example.Services;

import org.example.Models.Guest;
import org.example.Repositories.api.GuestRepositoryAPI;
import org.example.Repositories.api.Repository;

import java.util.List;
import java.util.Optional;

public class GuestService {
    private GuestRepositoryAPI guestRepository;

    public GuestService(GuestRepositoryAPI guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest addGuest(Guest guest) {
        return guestRepository.save(guest);
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
