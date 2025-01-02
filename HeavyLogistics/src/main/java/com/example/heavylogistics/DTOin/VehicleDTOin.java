package com.example.heavylogistics.DTOin;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VehicleDTOin {

    //Owner data
    @NotEmpty(message = "User National ID cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "National ID must be exactly 10 digits.")
    private String nationalId;

    @NotEmpty(message = "Name Vehicle Owner cannot be empty")
    private String nameVehicleOwner;



    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @NotNull(message = "Registration Date cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationAt = LocalDate.now();

    //Operation card data

    private Integer cardNumber;

    private LocalDateTime cardIssuanceDate;

    private LocalDateTime cardExpiryDate;

    private LocalDateTime cardRenewalDate;


    //Vehicle data

    @NotEmpty(message = "vehicleName should be not empty!")
    private String vehicleName;

    @NotEmpty(message = "vehicleType should be not empty!")
    private String vehicleType;

    @NotEmpty(message = "Vehicle Capacity is required")
    private String vehicleCapacity;

    @NotEmpty(message = "License Plate should be not empty!")
    private String LicensePlate;

    @NotNull(message = "PricePerDay Plate should be not empty!")
    @Positive(message = "PricePerDay number must be positive")
    private double  PricePerDay;

    @NotNull(message = "PricePerHour Plate should be not empty!")
    @Positive(message = "PricePerHour number must be positive")
    private double PricePerHour;

    @NotEmpty(message = "Location should be not empty!")
    private String Location;


    @NotEmpty(message = "yearOfManufacture should be not empty!")
    private String  yearOfManufacture;

    @NotEmpty(message = "vehicleColor should be not empty!")
    private String vehicleColor;






}
