package ru.vsu.cs.trufanov.Repositories.impl;

import ru.vsu.cs.trufanov.Models.Guest;
import ru.vsu.cs.trufanov.Models.Reservation;
import ru.vsu.cs.trufanov.Models.Room;
import ru.vsu.cs.trufanov.Repositories.api.ReservationRepositoryApi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationRepositoryImpl implements ReservationRepositoryApi {
    private final Connection connection;

    public ReservationRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Reservation save(Reservation reservation) {
        if (reservation.getId() == null) {
            return insertReservation(reservation);
        } else {
            return updateReservation(reservation);
        }
    }

    private Reservation insertReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (guest_id) VALUES (?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, reservation.getGuest().getId());
            stmt.executeUpdate();

            // Получаем сгенерированный ID бронирования
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    reservation.setId(rs.getLong(1));
                }
            }

            saveReservationRooms(reservation); // Сохраняем связанные комнаты

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    private Reservation updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET guest_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, reservation.getGuest().getId());
            stmt.setLong(2, reservation.getId());
            stmt.executeUpdate();

            // Обновляем связанные комнаты
            deleteReservationRooms(reservation.getId());
            saveReservationRooms(reservation);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        String sql = "SELECT guest_id FROM reservations WHERE id = ?";
        Reservation reservation = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Long guestId = rs.getLong("guest_id");
                    Guest guest = findGuestById(guestId);
                    List<Room> rooms = findRoomsByReservationId(id);
                    reservation = new Reservation(id, guest, rooms);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT id, guest_id FROM reservations";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long guestId = rs.getLong("guest_id");
                Guest guest = findGuestById(guestId);
                List<Room> rooms = findRoomsByReservationId(id);
                reservations.add(new Reservation(id, guest, rooms));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public void deleteById(Long id) {
        deleteReservationRooms(id); // Удаляем связанные комнаты
        String sql = "DELETE FROM reservations WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Вспомогательный метод для сохранения связанных комнат
    private void saveReservationRooms(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservation_rooms (reservation_id, room_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Room room : reservation.getRooms()) {
                stmt.setLong(1, reservation.getId());
                stmt.setLong(2, room.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    // Вспомогательный метод для удаления связанных комнат при обновлении брони
    private void deleteReservationRooms(Long reservationId) {
        String sql = "DELETE FROM reservation_rooms WHERE reservation_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, reservationId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для поиска гостя по ID
    private Guest findGuestById(Long guestId) {
        String sql = "SELECT * FROM guests WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, guestId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Guest(rs.getLong("id"), rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Метод для поиска всех комнат, связанных с конкретной бронью
    private List<Room> findRoomsByReservationId(Long reservationId) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT r.id, r.room_number FROM rooms r " +
                "JOIN reservation_rooms rr ON r.id = rr.room_id " +
                "WHERE rr.reservation_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, reservationId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    rooms.add(new Room(rs.getLong("id"), rs.getString("room_number")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
