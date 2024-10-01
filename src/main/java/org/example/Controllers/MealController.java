package org.example.Controllers;

import org.example.Entities.Meal;
import org.example.Services.MealService;

import java.util.Scanner;

public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    public void manageMeals(Scanner scanner) {
        System.out.println("1. Add Meal");
        System.out.println("2. View Meal");
        System.out.println("3. Update Meal");
        System.out.println("4. Delete Meal");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addMeal(scanner);
                break;
            case 2:
                viewMeal(scanner);
                break;
            case 3:
                updateMeal(scanner);
                break;
            case 4:
                deleteMeal(scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void addMeal(Scanner scanner) {
        System.out.print("Enter meal type: ");
        String mealType = scanner.nextLine();
        System.out.print("Enter meal price: ");
        double price = scanner.nextDouble();

        Meal meal = new Meal(0, mealType, price);
        mealService.addMeal(meal);
        System.out.println("Meal added: " + meal);
    }

    private void viewMeal(Scanner scanner) {
        System.out.print("Enter meal ID: ");
        int id = scanner.nextInt();
        Meal meal = mealService.findMealById(id);
        System.out.println(meal != null ? meal : "Meal not found");
    }

    private void updateMeal(Scanner scanner) {
        System.out.print("Enter meal ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter meal type: ");
        String mealType = scanner.nextLine();
        System.out.print("Enter meal price: ");
        double price = scanner.nextDouble();

        Meal meal = new Meal(id, mealType, price);
        mealService.updateMeal(id, meal);
        System.out.println("Meal updated: " + meal);
    }

    private void deleteMeal(Scanner scanner) {
        System.out.print("Enter meal ID: ");
        int id = scanner.nextInt();
        mealService.deleteMeal(id);
        System.out.println("Meal deleted.");
    }
}
