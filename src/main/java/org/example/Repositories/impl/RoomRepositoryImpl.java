package org.example.Repositories.impl;

import org.example.Models.Room;
import org.example.Repositories.api.RoomRepositoryApi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepositoryApi {
    private final Connection connection;

    public RoomRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Room save(Room room) {
        if (room.getId() == null) {
            return insert(room);
        } else {
            return update(room);
        }
    }

    private Room insert(Room room) {
        String sql = "INSERT INTO rooms (room_number) VALUES (?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getRoomNumber());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                room.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    private Room update(Room room) {
        String sql = "UPDATE rooms SET room_number = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getRoomNumber());
            stmt.setLong(2, room.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public Optional<Room> findById(Long id) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Room(rs.getLong("id"), rs.getString("room_number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rooms.add(new Room(rs.getLong("id"), rs.getString("room_number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM rooms WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
