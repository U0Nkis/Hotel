package org.example;

import org.example.Controllers.GuestController;
import org.example.Controllers.ReservationController;
import org.example.Controllers.RoomController;
import org.example.Repositories.impl.GuestRepositoryImpl;
import org.example.Repositories.impl.ReservationRepositoryImpl;
import org.example.Repositories.impl.RoomRepositoryImpl;
import org.example.Services.GuestService;
import org.example.Services.ReservationService;
import org.example.Services.RoomService;

import java.util.Scanner;

public class HotelApplication {
    public static void main(String[] args) {
        GuestService guestService = new GuestService(new GuestRepositoryImpl());
        RoomService roomService = new RoomService(new RoomRepositoryImpl());
        ReservationService reservationService = new ReservationService(new ReservationRepositoryImpl());

        Scanner scanner = new Scanner(System.in);

        GuestController guestController = new GuestController(guestService, scanner);
        RoomController roomController = new RoomController(roomService, scanner);
        ReservationController reservationController = new ReservationController(reservationService, guestService, roomService, scanner);

        while (true) {
            System.out.println("\n1. Управление гостями");
            System.out.println("2. Управление комнатами");
            System.out.println("3. Управление бронями");
            System.out.println("0. Выйти");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    guestMenu(guestController);
                    break;

                case 2:
                    roomMenu(roomController);
                    break;

                case 3:
                    reservationMenu(reservationController);
                    break;

                case 0:
                    System.exit(0);
            }
        }
    }

    private static void guestMenu(GuestController guestController) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Создать гостя");
            System.out.println("2. Показать всех гостей");
            System.out.println("3. Найти гостя по ID");
            System.out.println("4. Обновить гостя");
            System.out.println("5. Удалить гостя");
            System.out.println("0. Назад");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    guestController.createGuest();
                    break;
                case 2:
                    guestController.getAllGuests();
                    break;
                case 3:
                    guestController.getGuestById();
                    break;
                case 4:
                    guestController.updateGuest();
                    break;
                case 5:
                    guestController.deleteGuest();
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void roomMenu(RoomController roomController) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Создать комнату");
            System.out.println("2. Показать все комнаты");
            System.out.println("3. Найти комнату по ID");
            System.out.println("4. Обновить комнату");
            System.out.println("5. Удалить комнату");
            System.out.println("0. Назад");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    roomController.createRoom();
                    break;
                case 2:
                    roomController.getAllRooms();
                    break;
                case 3:
                    roomController.getRoomById();
                    break;
                case 4:
                    roomController.updateRoom();
                    break;
                case 5:
                    roomController.deleteRoom();
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void reservationMenu(ReservationController reservationController) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Создать бронь");
            System.out.println("2. Показать все брони");
            System.out.println("3. Найти бронь по ID");
            System.out.println("4. Обновить бронь");
            System.out.println("5. Удалить бронь");
            System.out.println("0. Назад");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationController.createReservation();
                    break;
                case 2:
                    reservationController.getAllReservations();
                    break;
                case 3:
                    reservationController.getReservationById();
                    break;
                case 4:
                    reservationController.updateReservation();
                    break;
                case 5:
                    reservationController.deleteReservation();
                    break;
                case 0:
                    return;
            }
        }
    }
}
