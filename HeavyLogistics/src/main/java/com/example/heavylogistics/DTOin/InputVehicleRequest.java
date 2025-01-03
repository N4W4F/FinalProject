package com.example.heavylogistics.DTOin;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InputVehicleRequest {

    @NotNull(message = "Request start time is required.")
    private LocalDateTime startTime;

    @NotNull(message = "Request end time is required.")
    private LocalDateTime endTime;

    private  Boolean withDrive;
}
