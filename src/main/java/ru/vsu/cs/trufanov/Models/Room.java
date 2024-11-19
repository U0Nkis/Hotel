package ru.vsu.cs.trufanov.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Room {
    private Long id;
    private String roomNumber;

    @JsonCreator
    public Room(@JsonProperty("id")Long id,@JsonProperty("roomNumber") String roomNumber) {
        this.id = id;
        this.roomNumber = roomNumber;
    }

    public Long getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }
}
