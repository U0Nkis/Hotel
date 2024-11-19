package ru.vsu.cs.trufanov.Controllers;

import ru.vsu.cs.trufanov.Models.Reservation;
import ru.vsu.cs.trufanov.Services.GuestService;
import ru.vsu.cs.trufanov.Services.ReservationService;
import ru.vsu.cs.trufanov.Services.RoomService;

import java.util.Arrays;
import java.util.Scanner;

public class ReservationController {
    private final ReservationService reservationService;
    private final GuestService guestService;
    private final RoomService roomService;
    private final Scanner scanner;

    public ReservationController(ReservationService reservationService, GuestService guestService, RoomService roomService, Scanner scanner) {
        this.reservationService = reservationService;
        this.guestService = guestService;
        this.roomService = roomService;
        this.scanner = scanner;
    }

    public void createReservation() {
        System.out.print("Введите ID гостя для брони: ");
        Long guestId = scanner.nextLong();

        var guest = guestService.findGuestById(guestId);
        if (guest.isEmpty()) {
            System.out.println("Гость с таким ID не найден.");
            return;
        }

        System.out.print("Введите ID комнат через запятую: ");
        String[] roomIds = scanner.next().split(",");
        var rooms = Arrays.stream(roomIds)
                .map(id -> roomService.findRoomById(Long.parseLong(id)).orElse(null))
                .toList();

        if (rooms.contains(null)) {
            System.out.println("Одна или несколько комнат не найдены.");
            return;
        }

        Reservation newReservation = new Reservation(null, guest.get(), rooms);
        Reservation savedReservation = reservationService.addReservation(newReservation);

        System.out.println("Бронь создана: " + savedReservation);
    }

    public void getAllReservations() {
        var reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("Список броней пуст.");
        } else {
            reservations.forEach(System.out::println);
        }
    }

    public void getReservationById() {
        System.out.print("Введите ID брони: ");
        Long id = scanner.nextLong();

        var reservation = reservationService.findReservationById(id);
        reservation.ifPresentOrElse(
                res -> System.out.println("Бронь найдена: " + res),
                () -> System.out.println("Бронь с таким ID не найдена.")
        );
    }

    public void updateReservation() {
        System.out.print("Введите ID брони для обновления: ");
        Long reservationId = scanner.nextLong();

        var reservation = reservationService.findReservationById(reservationId);
        if (reservation.isEmpty()) {
            System.out.println("Бронь с таким ID не найдена.");
            return;
        }

        System.out.print("Введите новый ID гостя: ");
        Long guestId = scanner.nextLong();
        var guest = guestService.findGuestById(guestId);
        if (guest.isEmpty()) {
            System.out.println("Гость с таким ID не найден.");
            return;
        }

        System.out.print("Введите новые ID комнат через запятую: ");
        String[] roomIds = scanner.next().split(",");
        var rooms = Arrays.stream(roomIds)
                .map(id -> roomService.findRoomById(Long.parseLong(id)).orElse(null))
                .toList();

        if (rooms.contains(null)) {
            System.out.println("Одна или несколько комнат не найдены.");
            return;
        }

        Reservation updatedReservation = new Reservation(reservationId, guest.get(), rooms);
        reservationService.addReservation(updatedReservation);
        System.out.println("Бронь обновлена: " + updatedReservation);
    }

    public void deleteReservation() {
        System.out.print("Введите ID брони для удаления: ");
        Long id = scanner.nextLong();

        reservationService.removeReservation(id);
        System.out.println("Бронь удалена.");
    }
}
