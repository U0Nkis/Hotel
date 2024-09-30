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

    public int getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", guest=" + guest +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}
