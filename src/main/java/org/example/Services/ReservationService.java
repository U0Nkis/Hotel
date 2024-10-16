package org.example.Services;

import org.example.Models.Reservation;
import org.example.Repositories.api.Repository;

import java.util.List;
import java.util.Optional;

public class ReservationService {
    private Repository<Reservation> reservationRepository;

    public ReservationService(Repository<Reservation> reservationRepository) {
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
