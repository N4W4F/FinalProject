package com.example.heavylogistics.Controller;

import com.example.heavylogistics.ApiResponse.ApiResponse;
import com.example.heavylogistics.DTOin.InputDriverReview;
import com.example.heavylogistics.DTOout.OutputDriverReview;
import com.example.heavylogistics.Service.DriverReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/heavy-logistics/driver")
public class DriverReviewController {

    private final DriverReviewService driverReviewService;


    @PostMapping("/create-review/{driverId}")
    public ResponseEntity<?> createReview(@RequestParam Integer customerId,
                                          @PathVariable Integer driverId,
                                          @RequestBody @Valid InputDriverReview inputDriverReview) {
        driverReviewService.createReview(customerId, driverId, inputDriverReview);
        return ResponseEntity.status(200).body(new ApiResponse("Review created successfully"));
    }

    @DeleteMapping("/delete-review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer reviewId) {
        driverReviewService.deleteReview(reviewId);
        return ResponseEntity.status(200).body(new ApiResponse("Review deleted successfully"));
    }

    // Endpoint to get all reviews for a specific driver
    @GetMapping("/all-reviews/{driverId}")
    public ResponseEntity<?> getReviewsForDriver(@PathVariable Integer driverId) {
        List<OutputDriverReview> reviews = driverReviewService.getReviewsForDriver(driverId);
        return ResponseEntity.status(200).body(reviews);
    }

    // Endpoint to get the average rating for a specific driver
    @GetMapping("/average-rating/{driverId}")
    public ResponseEntity<?> getAverageOfReviewRating(@PathVariable Integer driverId) {
        Double averageRation = driverReviewService.getAverageOfReviewRating(driverId);
        return ResponseEntity.status(200).body(averageRation);
    }

    // Endpoint to get all reviews by a specific customer
    @GetMapping("/customer-reviews/{customerId}")
    public ResponseEntity<?> getAllCustomerReviews(@PathVariable Integer customerId) {
        List<OutputDriverReview> reviews = driverReviewService.getAllCustomerReviews(customerId);
        return ResponseEntity.status(200).body(reviews);
    }

}
