package com.example.heavylogistics.DTOout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OutputDriverReview {

    private String customerName;

    private Integer rating;

    private String comment;

    private LocalDateTime createdAt;

}
