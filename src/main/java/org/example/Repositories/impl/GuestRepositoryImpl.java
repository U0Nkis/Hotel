package org.example.Repositories.impl;

import org.example.Entities.Guest;
import org.example.Repositories.api.Repository;

import java.util.*;
import java.util.Optional;

public class GuestRepositoryImpl implements Repository<Guest> {
    private Map<Long, Guest> guestMap = new HashMap<>();
    private Long idCounter = 0L;

    @Override
    public void save(Guest entity) {
        entity = new Guest(++idCounter, entity.getName());
        guestMap.put(entity.getId(), entity);
    }

    @Override
    public Optional<Guest> findById(Long id) {
        return Optional.ofNullable(guestMap.get(id));
    }

    @Override
    public List<Guest> findAll() {
        return new ArrayList<>(guestMap.values());
    }

    @Override
    public void deleteById(Long id) {
        guestMap.remove(id);
    }
}
