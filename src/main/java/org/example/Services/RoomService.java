package org.example.Services;

import org.example.Models.Room;
import org.example.Repositories.api.Repository;
import org.example.Repositories.api.RoomRepositoryApi;

import java.util.List;
import java.util.Optional;

public class RoomService {
    private RoomRepositoryApi roomRepository;

    public RoomService(RoomRepositoryApi roomRepository) {
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

