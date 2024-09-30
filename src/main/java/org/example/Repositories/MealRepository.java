package org.example.Repositories;

import org.example.Entities.Meal;

import java.util.HashMap;
import java.util.Map;

public class MealRepository {
    private final Map<Integer, Meal> mealStorage = new HashMap<>();
    private int nextId = 1;

    public Meal save(Meal meal) {
        if (meal.getId() == 0) {
            meal.setId(nextId++);
        }
        mealStorage.put(meal.getId(), meal);
        return meal;
    }

    public Meal findById(int id) {
        return mealStorage.get(id);
    }

    public Map<Integer, Meal> findAll() {
        return mealStorage;
    }

    public void deleteById(int id) {
        mealStorage.remove(id);
    }
}
