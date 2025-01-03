package com.example.heavylogistics.DTOout;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OutputVehicleDTO {

    @NotEmpty(message = "Vehicle name cannot be empty.")
    private String vehicleName;

    @NotEmpty(message = "Vehicle type cannot be empty.")
    private String vehicleType;

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
}

