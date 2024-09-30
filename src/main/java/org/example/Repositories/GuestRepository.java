package org.example.Repositories;

import org.example.Entities.Guest;

import java.util.HashMap;
import java.util.Map;

public class GuestRepository {
    private final Map<Integer, Guest> guestStorage = new HashMap<>();
    private int nextId = 1;

    // Создание или обновление гостя
    public Guest save(Guest guest) {
        if (guest.getId() == 0) {
            guest = new Guest(nextId++, guest.getFullName(), guest.getAddress(), guest.getPhoneNumber());
        }
        guestStorage.put(guest.getId(), guest);
        return guest;
    }

    // Поиск по ID
    public Guest findById(int id) {
        return guestStorage.get(id);
    }

    // Получить всех гостей
    public Map<Integer, Guest> findAll() {
        return guestStorage;
    }

    // Удаление по ID
    public void deleteById(int id) {
        guestStorage.remove(id);
    }
}
