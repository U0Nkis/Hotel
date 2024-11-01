package org.example.Repositories.impl;

import org.example.Models.Reservation;
import org.example.Repositories.api.Repository;
import org.example.Repositories.api.ReservationRepositoryApi;
import org.example.Repositories.api.RoomRepositoryApi;

import java.util.*;

public class ReservationRepositoryImpl implements ReservationRepositoryApi {
    @Override
    public Reservation save(Reservation entity) {
        return null;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Reservation> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }
}
