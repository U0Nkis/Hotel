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

    public void setId(int id) {
        this.id = id;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Meal{id=" + id + ", mealType='" + mealType + "', price=" + price + "}";
    }
}
