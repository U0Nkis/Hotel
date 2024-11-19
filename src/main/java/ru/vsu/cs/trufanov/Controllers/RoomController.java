package ru.vsu.cs.trufanov.Controllers;

import ru.vsu.cs.trufanov.Models.Room;
import ru.vsu.cs.trufanov.Services.RoomService;

import java.util.Scanner;

public class RoomController {
    private final RoomService roomService;
    private final Scanner scanner;

    public RoomController(RoomService roomService, Scanner scanner) {
        this.roomService = roomService;
        this.scanner = scanner;
    }

    public void createRoom() {
        System.out.print("Введите номер комнаты: ");
        String roomNumber = scanner.next();
        Room newRoom = new Room(null, roomNumber);
        Room savedRoom = roomService.addRoom(newRoom);
        System.out.println("Комната создана: " + savedRoom);
    }

    public void getAllRooms() {
        var rooms = roomService.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("Список комнат пуст.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    public void getRoomById() {
        System.out.print("Введите ID комнаты: ");
        Long id = scanner.nextLong();
        var room = roomService.findRoomById(id);
        if (room.isPresent()) {
            System.out.println("Комната найдена: " + room.get());
        } else {
            System.out.println("Комната с таким ID не найдена.");
        }
    }

    public void updateRoom() {
        System.out.print("Введите ID комнаты для обновления: ");
        Long roomId = scanner.nextLong();
        var room = roomService.findRoomById(roomId);

        if (room.isPresent()) {
            System.out.print("Введите новый номер комнаты: ");
            String newRoomNumber = scanner.next();
            Room updatedRoom = new Room(roomId, newRoomNumber);
            roomService.addRoom(updatedRoom);
            System.out.println("Комната обновлена: " + updatedRoom);
        } else {
            System.out.println("Комната с таким ID не найдена.");
        }
    }

    public void deleteRoom() {
        System.out.print("Введите ID комнаты для удаления: ");
        Long id = scanner.nextLong();
        roomService.removeRoom(id);
        System.out.println("Комната удалена.");
    }
}