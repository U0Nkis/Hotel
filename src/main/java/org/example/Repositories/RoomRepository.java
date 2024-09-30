package org.example.Repositories;

import org.example.Entities.Room;

import java.util.HashMap;
import java.util.Map;

public class RoomRepository {
    private final Map<Integer, Room> roomStorage = new HashMap<>();
    private int nextId = 1;

    public Room save(Room room) {
        if (room.getId() == 0) {
            room = new Room(nextId++, room.getRoomNumber(), room.getType(), room.getPricePerNight(), room.isAvailable());
        }
        roomStorage.put(room.getId(), room);
        return room;
    }

    public Room findById(int id) {
        return roomStorage.get(id);
    }

    public Map<Integer, Room> findAll() {
        return roomStorage;
    }

    public void deleteById(int id) {
        roomStorage.remove(id);
    }
}
