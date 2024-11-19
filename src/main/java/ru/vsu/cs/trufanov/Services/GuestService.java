package ru.vsu.cs.trufanov.Services;

import lombok.RequiredArgsConstructor;
import ru.vsu.cs.trufanov.Models.Guest;
import ru.vsu.cs.trufanov.Repositories.api.GuestRepositoryApi;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GuestService {

    private GuestRepositoryApi guestRepository;

    public GuestService(GuestRepositoryApi guestRepository) {
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
