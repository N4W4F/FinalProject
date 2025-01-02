package com.example.heavylogistics.DTOin;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InputDriverReview {

    @NotNull(message = "Rating is required.")
    @Min(value = 1, message = "Rating must be at least 1.")
    @Max(value = 5, message = "Rating must be at most 5.")
    private Integer rating;

    @NotEmpty(message = "Comment cannot be blank.")
    @Size(max = 500, message = "Comment must not exceed 500 characters.")
    private String comment;
}

