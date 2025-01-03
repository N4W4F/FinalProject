package com.example.heavylogistics.DTOout;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OutputVehicleReviewDTO {

    private Integer id;

    @NotNull(message = "Rating is required.")
    @Min(value = 1, message = "Rating must be at least 1.")
    @Max(value = 5, message = "Rating must be at most 5.")
    private Double rating;

    @NotEmpty(message = "Comment cannot be blank.")
    @Size(max = 500, message = "Comment must not exceed 500 characters.")
    private String comments;

    @NotEmpty(message = "vehicleName cannot be blank.")
    private String customerName;

    @NotEmpty(message = "vehicleName cannot be blank.")
    private String vehicleName;
}
