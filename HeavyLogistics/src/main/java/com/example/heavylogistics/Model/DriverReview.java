package com.example.heavylogistics.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DriverReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Rating is required.")
    @Min(value = 1, message = "Rating must be at least 1.")
    @Max(value = 5, message = "Rating must be at most 5.")
    private Integer rating;

    @NotEmpty(message = "Comment cannot be blank.")
    @Size(max = 500, message = "Comment must not exceed 500 characters.")
    private String comment;

    @NotNull
    private LocalDateTime createdAt;


    // Relations


    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;



}
