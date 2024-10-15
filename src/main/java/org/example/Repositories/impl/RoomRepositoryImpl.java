package org.example.Repositories.impl;

import org.example.Entities.Room;
import org.example.Repositories.api.Repository;

import java.util.*;

public class RoomRepositoryImpl implements Repository<Room> {
    private Map<Long, Room> roomMap = new HashMap<>();
    private Long idCounter = 0L;

    @Override
    public void save(Room entity) {
        entity = new Room(++idCounter, entity.getRoomNumber());
        roomMap.put(entity.getId(), entity);
    }

    @Override
    public Optional<Room> findById(Long id) {
        return Optional.ofNullable(roomMap.get(id));
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(roomMap.values());
    }

    @Override
    public void deleteById(Long id) {
        roomMap.remove(id);
    }
}
