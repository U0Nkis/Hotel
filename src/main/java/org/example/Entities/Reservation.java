package org.example.Entities;

import java.util.List;

public class Reservation {
    private Long id;
    private Guest guest;
    private List<Room> rooms;

    public Reservation(Long id, Guest guest, List<Room> rooms) {
        this.id = id;
        this.guest = guest;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", guest=" + guest +
                ", rooms=" + rooms +
                '}';
    }
}
