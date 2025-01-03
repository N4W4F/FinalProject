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
@Setter
@Getter
@Entity
public class DriverRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "vehicleName should be not empty!")
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String vehicleName;

    @NotEmpty(message = "vehicleType should be not empty!")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String vehicleType;

    @NotEmpty(message = "Vehicle Capacity is required")
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String vehicleCapacity;

    @NotEmpty(message = "Starting location is required.")
    @Size(max = 200, message = "Starting location must not exceed 200 characters.")
    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String startLocation;

    @NotEmpty(message = "Destination location is required.")
    @Size(max = 200, message = "Destination location must not exceed 200 characters.")
    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String destinationLocation;

    @NotNull(message = "Request start time is required.")
    @Column(nullable = false)
    private LocalDateTime startTime;

    @NotNull(message = "Request end time is required.")
    @Column(nullable = false)
    private LocalDateTime endTime;

    @NotNull(message = "Expected total amount is required.")
    @Positive(message = "Expected total amount must be a positive value.")
    @Column(nullable = false)
    private Double totalAmount;

    @Size(max = 500, message = "Customer note must not exceed 500 characters.")
    @Column(columnDefinition = "VARCHAR(500)")
    private String customerNote;

    @Pattern(regexp = "PENDING|APPROVED|REJECTED|IN PROGRESS|COMPLETED|CANACELLED")
    @Column(columnDefinition = "varchar(15) default 'PENDING'")
    private String driverRequestStatus;


    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
