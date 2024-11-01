package org.example.Models;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
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
