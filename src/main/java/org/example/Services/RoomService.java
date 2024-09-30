package org.example.Services;

import org.example.Entities.Room;
import org.example.Repositories.RoomRepository;

import java.util.Map;

public class RoomService {
    private final RoomRepository roomRepository = new RoomRepository();

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room findRoomById(int id) {
        return roomRepository.findById(id);
    }

    public Map<Integer, Room> findAllRooms() {
        return roomRepository.findAll();
    }

    public void updateRoom(int id, Room room) {
        roomRepository.save(room);
    }

    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }
}
