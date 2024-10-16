package org.example.Controllers;

import org.example.Models.Guest;
import org.example.Services.GuestService;

import java.util.Scanner;

public class GuestController {
    private final GuestService guestService;
    private final Scanner scanner;

    public GuestController(GuestService guestService, Scanner scanner) {
        this.guestService = guestService;
        this.scanner = scanner;
    }

    // Create
    public void createGuest() {
        System.out.print("Введите имя гостя: ");
        String guestName = scanner.next();
        Guest newGuest = new Guest(null, guestName);
        Guest savedGuest = guestService.addGuest(newGuest);
        System.out.println("Гость создан: " + savedGuest);
    }

    // Read (find all guests)
    public void getAllGuests() {
        var guests = guestService.getAllGuests();
        if (guests.isEmpty()) {
            System.out.println("Список гостей пуст.");
        } else {
            guests.forEach(System.out::println);
        }
    }

    // Read (find guest by ID)
    public void getGuestById() {
        System.out.print("Введите ID гостя: ");
        Long id = scanner.nextLong();
        var guest = guestService.findGuestById(id);
        if (guest.isPresent()) {
            System.out.println("Гость найден: " + guest.get());
        } else {
            System.out.println("Гость с таким ID не найден.");
        }
    }

    // Update
    public void updateGuest() {
        System.out.print("Введите ID гостя для обновления: ");
        Long guestId = scanner.nextLong();
        var guest = guestService.findGuestById(guestId);

        if (guest.isPresent()) {
            System.out.print("Введите новое имя гостя: ");
            String newName = scanner.next();
            Guest updatedGuest = new Guest(guestId, newName);
            guestService.addGuest(updatedGuest);
            System.out.println("Гость обновлён: " + updatedGuest);
        } else {
            System.out.println("Гость с таким ID не найден.");
        }
    }

    // Delete
    public void deleteGuest() {
        System.out.print("Введите ID гостя для удаления: ");
        Long id = scanner.nextLong();
        guestService.removeGuest(id);
        System.out.println("Гость удалён.");
    }
}
