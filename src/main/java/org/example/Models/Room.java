package org.example.Models;

public class Room {
    private Long id;
    private String roomNumber;

    public Room(Long id, String roomNumber) {
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
