package org.example.Services;

import org.example.Entities.Meal;
import org.example.Repositories.MealRepository;

import java.util.Map;

public class MealService {
    private final MealRepository mealRepository = new MealRepository();

    public Meal addMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public Meal findMealById(int id) {
        return mealRepository.findById(id);
    }

    public Map<Integer, Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public void updateMeal(int id, Meal meal) {
        mealRepository.save(meal);
    }

    public void deleteMeal(int id) {
        mealRepository.deleteById(id);
    }
}
