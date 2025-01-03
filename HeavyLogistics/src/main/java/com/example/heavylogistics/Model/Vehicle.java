package com.example.heavylogistics.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Owner data
    @Column(nullable = false)
    private String ownerNationalId;


    @NotEmpty(message = "Name Vehicle Owner cannot be empty")
    @Column(nullable = false)
    private String nameVehicleOwner;


    //Operation card data

    @NotEmpty(message = "card Number cannot be empty.")
    @Column(nullable = false)
    private Integer cardNumber;

    @NotEmpty(message = "card Issuance Date cannot be empty.")
    @Column(nullable = false)
    private LocalDateTime cardIssuanceDate;

    @NotEmpty(message = "card Expiry Date cannot be empty.")
    @Column(nullable = false)
    private LocalDateTime cardExpiryDate;

    @NotEmpty(message = "card Renewal Date cannot be empty.")
    @Column(nullable = false)
    private LocalDateTime cardRenewalDate;


    //Vehicle data
    @NotEmpty(message = "Vehicle name cannot be empty.")
    @Column(nullable = false)
    private String vehicleName;

    @NotEmpty(message = "Vehicle type cannot be empty.")
    @Column(nullable = false)
    private String vehicleType;

    @NotEmpty(message = "License plate is required.")
    @Column(nullable = false, unique = true)
    private String licensePlate;

    @NotNull(message = "Price per day is required.")
    @Positive(message = "Price per day must be a positive number.")
    private Double pricePerDay;

    @NotNull(message = "Price per hour is required.")
    @Positive(message = "Price per hour must be a positive number.")
    private Double pricePerHour;

    @NotEmpty(message = "Vehicle capacity is required.")
    private String capacity;

    @NotEmpty(message = "Vehicle color cannot be empty.")
    private String color;

    @NotEmpty(message = "Location is required.")
    private String location;

    @NotEmpty(message = "Year of manufacture cannot be empty.")
    private String yearOfManufacture;

    // Relations

    @ManyToOne
    private Lessor lessor;

    @OneToMany
    @JsonIgnore
    private Set<VehicleReview> vehicleReviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private Set<VehicleSchedule> vehicleSchedules;

    @OneToOne
    private VehicleRequest vehicleRequest;
}
