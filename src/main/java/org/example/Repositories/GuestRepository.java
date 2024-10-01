package org.example.Repositories;

import org.example.Entities.Guest;

import java.util.HashMap;
import java.util.Map;

public class GuestRepository {
    private final Map<Integer, Guest> guestStorage = new HashMap<>();
    private int nextId = 1;

    public Guest save(Guest guest) {
        if (guest.getId() == 0) {
            guest.setId(nextId++);
        }
        guestStorage.put(guest.getId(), guest);
        return guest;
    }

    public Guest findById(int id) {
        return guestStorage.get(id);
    }

    public Map<Integer, Guest> findAll() {
        return guestStorage;
    }

    public void deleteById(int id) {
        guestStorage.remove(id);
    }
}
