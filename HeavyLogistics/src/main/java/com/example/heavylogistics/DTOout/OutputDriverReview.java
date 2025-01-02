package com.example.heavylogistics.OutputDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class OutputDriverReview {

    private String driverName;

    private Integer rating;

    private String comment;

    private String customerName;

    private String customerId;

    private LocalDateTime createdAt;

}
