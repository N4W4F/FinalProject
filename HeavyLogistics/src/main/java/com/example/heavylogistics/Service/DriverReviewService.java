package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.DTOin.InputDriverReview;
import com.example.heavylogistics.DTOout.OutputDriverReview;
import com.example.heavylogistics.Model.Customer;
import com.example.heavylogistics.Model.Driver;
import com.example.heavylogistics.Model.DriverReview;

import com.example.heavylogistics.Repository.CustomerRepository;
import com.example.heavylogistics.Repository.DriverRepository;
import com.example.heavylogistics.Repository.DriverRequestRepository;
import com.example.heavylogistics.Repository.DriverReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverReviewService {

    private final DriverReviewRepository driverReviewRepository;
    private final DriverRepository driverRepository;
    private final CustomerRepository customerRepository;
    private final DriverRequestRepository driverRequestRepository;

    // Create a new review
    public void createReview(Integer customerId, Integer driverId, InputDriverReview inputDriverReview) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found.");
        }

        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw new ApiException("Driver not found.");
        }

        // Check if the customer has a completed request with the driver
        boolean hasCompletedRequest = driverRequestRepository.existsByCustomerIdAndDriverIdAndDriverRequestStatus(
                customerId, driverId, "COMPLETED");

        if (!hasCompletedRequest) {
            throw new ApiException("You cannot review this driver without a completed request.");
        }

        DriverReview driverReview = new DriverReview();
        driverReview.setRating(inputDriverReview.getRating());
        driverReview.setComment(inputDriverReview.getComment());
        driverReview.setCreatedAt(LocalDateTime.now());
        driverReview.setDriver(driver);
        driverReview.setCustomer(customer);

        driverReviewRepository.save(driverReview);
    }

    // Delete a review
    public void deleteReview(Integer reviewId) {
        DriverReview driverReview = driverReviewRepository.findDriverReviewById(reviewId);
        if (driverReview == null) {
            throw new ApiException("Driver review not found.");
        }
        driverReviewRepository.delete(driverReview);
    }

    // EndPoint
    // Get all reviews for a specific driver
    public List<OutputDriverReview> getReviewsForDriver(Integer driverId) {
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw new ApiException("Driver not found.");
        }

        List<DriverReview> driverReviews = driverReviewRepository.findDriverReviewByDriverId(driverId);
        List<OutputDriverReview> outputDriverReviews = new ArrayList<>();
        for (DriverReview driverReview : driverReviews) {
            OutputDriverReview outputDriverReview = convertToOutputDriverReview(driverReview);
            outputDriverReviews.add(outputDriverReview);
        }

        return outputDriverReviews;
    }

    //Endpoint
    public Double getAverageOfReviewRating(Integer driverId) {
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw new ApiException("Driver not found.");
        }

        List<DriverReview> reviews = driverReviewRepository.findDriverReviewByDriverId(driverId);

        if (reviews.isEmpty()) {
            throw new ApiException("No reviews available for this driver.");
        }
        // Calculate average rating
        double totalRating = 0.0;
        for (DriverReview review : reviews) {
            totalRating += review.getRating();
        }
        double averageRating = totalRating / reviews.size();

        return averageRating;
    }

    // Endpoint
    public List<OutputDriverReview> getAllCustomerReviews(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found.");
        }
        List<DriverReview> driverReviews= driverReviewRepository.findByCustomerId(customerId);
        List<OutputDriverReview> outputDriverReviews = new ArrayList<>();
        for (DriverReview driverReview : driverReviews) {
            OutputDriverReview outputDriverReview1 = convertToOutputDriverReview(driverReview);
            outputDriverReviews.add(outputDriverReview1);
        }
        return outputDriverReviews;
    }



    public OutputDriverReview convertToOutputDriverReview(DriverReview driverReview) {
        Customer customer = customerRepository.findCustomerById(driverReview.getCustomer().getId());
        OutputDriverReview output = new OutputDriverReview();
        output.setCustomerName(customer.getName());
        output.setRating(driverReview.getRating());
        output.setComment(driverReview.getComment());
        output.setCreatedAt(driverReview.getCreatedAt());
        return output;
    }


}
