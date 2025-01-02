package com.example.heavylogistics.OutputDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OutputDriverProfile {


    private String name;

    private String bioDescription;

    private Integer yearsOfExperience;

    private Double pricePerHour;

    private Double pricePerDay;

    private String region;

    private Boolean isAvailable;


}
