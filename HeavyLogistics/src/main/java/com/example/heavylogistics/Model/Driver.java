package com.example.heavylogistics.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Driver {

    @Id
    private Integer id;

    @NotEmpty(message = "Driver name is required")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    @Size(min = 2, max = 20, message = "Driver name must be between 2 and 20 characters")
    private String driverName;

    @NotEmpty(message = "License number is required.")
    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNIQUE")
    private String licenseNumber;

    //check type of license
    @NotEmpty(message = "License type is required.")
    @Size(max = 40, message = "License type must not exceed 40 characters.")
    @Column(columnDefinition = "VARCHAR(40) NOT NULL")
    private String licenseType;

    @NotNull(message = "License expiration date is required.")
    @Future(message = "License expiration date must be a future date.")
    @Column(name = "license_expiration_date", nullable = false)
    private LocalDate licenseExpirationDate;



    // Relations


    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "driver")
    private DriverProfile driverProfile;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    @JsonIgnore
    private Set<DriverRequest> driverRequests;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    @JsonIgnore
    private Set<DriverReview> driverReviews;



}
