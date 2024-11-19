package ru.vsu.cs.trufanov;

import ru.vsu.cs.trufanov.Config.DatabaseConfig;
import ru.vsu.cs.trufanov.Controllers.GuestController;
import ru.vsu.cs.trufanov.Controllers.RoomController;
import ru.vsu.cs.trufanov.Controllers.ReservationController;
import ru.vsu.cs.trufanov.Repositories.impl.GuestRepositoryImpl;
import ru.vsu.cs.trufanov.Repositories.impl.RoomRepositoryImpl;
import ru.vsu.cs.trufanov.Repositories.impl.ReservationRepositoryImpl;
import ru.vsu.cs.trufanov.Services.GuestService;
import ru.vsu.cs.trufanov.Services.RoomService;
import ru.vsu.cs.trufanov.Services.ReservationService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class HotelApplication {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            GuestService guestService = new GuestService(new GuestRepositoryImpl(connection));
            RoomService roomService = new RoomService(new RoomRepositoryImpl(connection));
            ReservationService reservationService = new ReservationService(new ReservationRepositoryImpl(connection));

            Scanner scanner = new Scanner(System.in);

            GuestController guestController = new GuestController(guestService, scanner);
            RoomController roomController = new RoomController(roomService, scanner);
            ReservationController reservationController = new ReservationController(reservationService, guestService, roomService, scanner);

            while (true) {
                System.out.println("\n1. Управление гостями");
                System.out.println("2. Управление комнатами");
                System.out.println("3. Управление бронями");
                System.out.println("0. Выйти");

                int choice = getIntInput(scanner);

                switch (choice) {
                    case 1 -> guestMenu(guestController);
                    case 2 -> roomMenu(roomController);
                    case 3 -> reservationMenu(reservationController);
                    case 0 -> {
                        return; // Выйти из приложения
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректное число.");
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

            int choice = getIntInput(scanner);

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

            int choice = getIntInput(scanner);

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

            int choice = getIntInput(scanner);

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
