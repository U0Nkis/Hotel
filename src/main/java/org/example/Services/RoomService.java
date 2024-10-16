package org.example.Services;

import org.example.Models.Room;
import org.example.Repositories.api.Repository;

import java.util.List;
import java.util.Optional;

public class RoomService {
    private Repository<Room> roomRepository;

    public RoomService(Repository<Room> roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Optional<Room> findRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void removeRoom(Long id) {
        roomRepository.deleteById(id);
    }
}

