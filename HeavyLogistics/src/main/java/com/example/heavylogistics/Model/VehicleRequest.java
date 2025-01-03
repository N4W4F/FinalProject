package com.example.heavylogistics.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {
    @Id
    private Integer id;

    @NotNull(message = "Request start time is required.")
    @Column(nullable = false)
    private LocalDateTime startTime;

    @NotNull(message = "Request end time is required.")
    @Column(nullable = false)
    private LocalDateTime endTime;

    @Positive(message = "Expected total amount must be a positive value.")
    @Column(nullable = false)
    private Double totalAmountVehicle;

    @Positive(message = "Expected total amount must be a positive value.")
    @Column(nullable = false)
    private Double totalAmountDriver;

    @Positive(message = "Negotiation Price number must be positive")
    @Column(columnDefinition = "DECIMAL NOT NULL")
    private Double negotiationPrice;

    @NotEmpty(message = "Request Status should be not empty")
    @Pattern(regexp = "PENDING|APPROVED|REJECTED|COMPLETED|CANACELLED")
    @Column(columnDefinition = "varchar(15) not null")
    private String requestStatus;

    private  Boolean withDrive;

    @NotNull(message = "Date cannot be null")
    @Column
    private LocalDateTime requestDate;


    // Relations

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vehicleRequest")
    private RentalContract rentalContract;
}
