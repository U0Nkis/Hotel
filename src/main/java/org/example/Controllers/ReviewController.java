package org.example.Controllers;

import org.example.Entities.Review;
import org.example.Services.GuestService;
import org.example.Services.ReviewService;

import java.util.Scanner;

public class ReviewController {
    private final ReviewService reviewService;
    private final GuestService guestService;

    public ReviewController(ReviewService reviewService, GuestService guestService) {
        this.reviewService = reviewService;
        this.guestService = guestService;
    }

    public void manageReviews(Scanner scanner) {
        System.out.println("1. Add Review");
        System.out.println("2. View Review");
        System.out.println("3. Update Review");
        System.out.println("4. Delete Review");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addReview(scanner);
                break;
            case 2:
                viewReview(scanner);
                break;
            case 3:
                updateReview(scanner);
                break;
            case 4:
                deleteReview(scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void addReview(Scanner scanner) {
        System.out.print("Enter guest ID: ");
        int guestId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter comment: ");
        String comment = scanner.nextLine();
        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();

        Review review = reviewService.addReview(guestId, comment, rating);
        System.out.println("Review added: " + review);
    }

    private void viewReview(Scanner scanner) {
        System.out.print("Enter review ID: ");
        int id = scanner.nextInt();
        Review review = reviewService.findReviewById(id);
        System.out.println(review != null ? review : "Review not found");
    }

    private void updateReview(Scanner scanner) {
        System.out.print("Enter review ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter guest ID: ");
        int guestId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter comment: ");
        String comment = scanner.nextLine();
        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();

        Review review = new Review(id, guestService.findGuestById(guestId), comment, rating);
        reviewService.updateReview(id, review);
        System.out.println("Review updated: " + review);
    }

    private void deleteReview(Scanner scanner) {
        System.out.print("Enter review ID: ");
        int id = scanner.nextInt();
        reviewService.deleteReview(id);
        System.out.println("Review deleted.");
    }
}
