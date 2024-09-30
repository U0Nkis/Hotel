package org.example.Entities;

public class Meal {
    private int id;
    private String mealType;
    private double price;

    public Meal(int id, String mealType, double price) {
        this.id = id;
        this.mealType = mealType;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getMealType() {
        return mealType;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", mealType='" + mealType + '\'' +
                ", price=" + price +
                '}';
    }
}
