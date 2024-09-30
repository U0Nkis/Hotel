package org.example.Repositories;

import org.example.Entities.Review;

import java.util.HashMap;
import java.util.Map;

public class ReviewRepository {
    private final Map<Integer, Review> reviewStorage = new HashMap<>();
    private int nextId = 1;

    // Создание или обновление отзыва
    public Review save(Review review) {
        if (review.getId() == 0) {
            review = new Review(nextId++, review.getGuest(), review.getComment(), review.getRating());
        }
        reviewStorage.put(review.getId(), review);
        return review;
    }

    // Поиск по ID
    public Review findById(int id) {
        return reviewStorage.get(id);
    }

    // Получить все отзывы
    public Map<Integer, Review> findAll() {
        return reviewStorage;
    }

    // Удаление по ID
    public void deleteById(int id) {
        reviewStorage.remove(id);
    }
}
