package ru.vsu.cs.trufanov.Services;

import ru.vsu.cs.trufanov.Models.Reservation;
import ru.vsu.cs.trufanov.Repositories.api.ReservationRepositoryApi;

import java.util.List;
import java.util.Optional;

public class ReservationService {
    private ReservationRepositoryApi reservationRepository;

    public ReservationService(ReservationRepositoryApi reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> findReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void removeReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
