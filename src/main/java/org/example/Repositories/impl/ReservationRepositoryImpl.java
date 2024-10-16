package org.example.Repositories.impl;

import org.example.Models.Reservation;
import org.example.Repositories.api.Repository;

import java.util.*;

public class ReservationRepositoryImpl implements Repository<Reservation> {
    private Map<Long, Reservation> reservationMap = new HashMap<>();
    private Long idCounter = 0L;

    @Override
    public Reservation save(Reservation entity) {
        if (entity.getId() == null) {
            entity = new Reservation(++idCounter, entity.getGuest(), entity.getRooms());
        }
        reservationMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return Optional.ofNullable(reservationMap.get(id));
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(reservationMap.values());
    }

    @Override
    public void deleteById(Long id) {
        reservationMap.remove(id);
    }
}
