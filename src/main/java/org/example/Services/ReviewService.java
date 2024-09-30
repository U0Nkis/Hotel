package org.example.Services;

import org.example.Entities.Guest;
import org.example.Entities.Review;
import org.example.Repositories.ReviewRepository;

import java.util.Map;

public class ReviewService {
    private final ReviewRepository reviewRepository = new ReviewRepository();
    private final GuestService guestService = new GuestService();

    public Review addReview(int guestId, String comment, int rating) {
        Guest guest = guestService.findGuestById(guestId);
        Review review = new Review(0, guest, comment, rating);
        return reviewRepository.save(review);
    }

    public Review findReviewById(int id) {
        return reviewRepository.findById(id);
    }

    public Map<Integer, Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public void updateReview(int id, Review review) {
        reviewRepository.save(review);
    }

    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}
