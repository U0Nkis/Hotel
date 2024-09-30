package org.example.Entities;

public class Room {
    private int id;
    private String roomNumber;
    private String type;
    private double pricePerNight;
    private boolean isAvailable;

    public Room(int id, String roomNumber, String type, double pricePerNight, boolean isAvailable) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Room{id=" + id + ", roomNumber='" + roomNumber + "', type='" + type + "', pricePerNight=" + pricePerNight + ", isAvailable=" + isAvailable + "}";
    }
}

