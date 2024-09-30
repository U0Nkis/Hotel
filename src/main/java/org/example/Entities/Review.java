package org.example.Entities;

public class Review {
    private int id;
    private Guest guest;
    private String comment;
    private int rating;

    public Review(int id, Guest guest, String comment, int rating) {
        this.id = id;
        this.guest = guest;
        this.comment = comment;
        this.rating = rating;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{id=" + id + ", guest=" + guest + ", comment='" + comment + "', rating=" + rating + "}";
    }
}
