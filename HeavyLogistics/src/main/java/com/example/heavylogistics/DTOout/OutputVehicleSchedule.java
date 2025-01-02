package com.example.heavylogistics.DTOout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputVehicleSchedule {
    private LocalDate startTime;
    private LocalDate endTime;
    private Boolean availabilityStatus;
}
