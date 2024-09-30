package org.example.Entities;

public class Booking {
    private int id;
    private Guest guest;
    private Room room;
    private String startDate;
    private String endDate;

    public Booking(int id, Guest guest, Room room, String startDate, String endDate) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Booking{id=" + id + ", guest=" + guest + ", room=" + room + ", startDate='" + startDate + "', endDate='" + endDate + "'}";
    }
}
