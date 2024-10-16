package org.example.Repositories.impl;

import org.example.Models.Guest;
import org.example.Repositories.api.Repository;

import java.util.*;
import java.util.Optional;

public class GuestRepositoryImpl implements Repository<Guest> {
    private Map<Long, Guest> guestMap = new HashMap<>();
    private Long idCounter = 0L;

    @Override
    public Guest save(Guest entity) {
        if (entity.getId() == null) {
            entity = new Guest(++idCounter, entity.getName());
        }
        guestMap.put(entity.getId(), entity);
        return entity;
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
