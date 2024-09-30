package org.example.Repositories;

import org.example.Entities.Review;

import java.util.HashMap;
import java.util.Map;

public class ReviewRepository {
    private final Map<Integer, Review> reviewStorage = new HashMap<>();
    private int nextId = 1;

    public Review save(Review review) {
        if (review.getId() == 0) {
            review.setId(nextId++);
        }
        reviewStorage.put(review.getId(), review);
        return review;
    }

    public Review findById(int id) {
        return reviewStorage.get(id);
    }

    public Map<Integer, Review> findAll() {
        return reviewStorage;
    }

    public void deleteById(int id) {
        reviewStorage.remove(id);
    }
}
