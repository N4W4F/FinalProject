package com.example.heavylogistics.DTOin;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InputDriverRequest {


    @NotEmpty(message = "vehicleName should be not empty!")
    private String vehicleName;

    @NotEmpty(message = "vehicleType should be not empty!")
    private String vehicleType;

    @NotEmpty(message = "Vehicle Capacity is required")
    private String vehicleCapacity;

    @NotEmpty(message = "Starting location is required.")
    @Size(max = 200, message = "Starting location must not exceed 200 characters.")
    private String startLocation;

    @NotEmpty(message = "Destination location is required.")
    @Size(max = 200, message = "Destination location must not exceed 200 characters.")
    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String destinationLocation;

    @NotNull(message = "Request start time is required.")
    private LocalDateTime startTime;

    @NotNull(message = "Request end time is required.")
    private LocalDateTime endTime;

    @Size(max = 500, message = "Customer note must not exceed 500 characters.")
    private String customerNote;


}
