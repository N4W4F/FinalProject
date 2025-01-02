package com.example.heavylogistics.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DriverProfile {

    @Id
    private Integer id;

    @NotEmpty(message = "Bio description is required.")
    @Size(max = 500, message = "Bio description must not exceed 500 characters.")
    @Column(columnDefinition = "VARCHAR(500) NOT NULL")
    private String bioDescription;

    @NotNull(message = "Years of experience are required.")
    @Min(value = 0, message = "Years of experience must be 0 or more.")
    @Max(value = 50, message = "Years of experience cannot exceed 50 years.")
    @Column(nullable = false)
    private Integer yearsOfExperience;

    @NotNull(message = "Price per hour is required.")
    @Positive(message = "Price per hour must be a positive value.")
    @Column(nullable = false)
    private Double pricePerHour;

    @NotNull(message = "Price per day is required.")
    @Positive(message = "Price per day must be a positive value.")
    @Column(nullable = false)
    private Double pricePerDay;

    @NotBlank(message = "Region is required.")
    @Size(max = 100, message = "Region must not exceed 100 characters.")
    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String region;

    //    @NotNull(message = "Available status is required.")
    private Boolean isAvailable;


    // Relations

    @OneToOne
    @MapsId
    @JsonIgnore
    private Driver driver;






}
