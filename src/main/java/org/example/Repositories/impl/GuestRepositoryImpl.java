package org.example.Repositories.impl;

import org.example.Models.Guest;
import org.example.Repositories.api.GuestRepositoryAPI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestRepositoryImpl implements GuestRepositoryAPI {
    private final Connection connection;

    public GuestRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Guest save(Guest guest) {
        if (guest.getId() == null) {
            return insert(guest);
        } else {
            return update(guest);
        }
    }

    private Guest insert(Guest guest) {
        String sql = "INSERT INTO guests (name) VALUES (?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, guest.getName());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                guest.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    private Guest update(Guest guest) {
        String sql = "UPDATE guests SET name = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, guest.getName());
            stmt.setLong(2, guest.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    @Override
    public Optional<Guest> findById(Long id) {
        String sql = "SELECT * FROM guests WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Guest(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Guest> findAll() {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM guests";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                guests.add(new Guest(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM guests WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
