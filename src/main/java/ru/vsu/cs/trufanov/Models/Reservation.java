package ru.vsu.cs.trufanov.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Reservation {
    private Long id;
    private Guest guest;
    private List<Room> rooms;

    @JsonCreator
    public Reservation(@JsonProperty("id") Long id, @JsonProperty("guest") Guest guest, @JsonProperty("list of rooms") List<Room> rooms) {
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
