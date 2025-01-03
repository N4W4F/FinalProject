package com.example.heavylogistics.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Rating is required.")
    @Min(value = 1, message = "Rating must be at least 1.")
    @Max(value = 5, message = "Rating must be at most 5.")
    @Column
    private Double rating;

    @NotEmpty(message = "Comment cannot be blank.")
    @Size(max = 500, message = "Comment must not exceed 500 characters.")
    @Column(columnDefinition = "varchar(100) not null")
    private String comments;



    // Relations

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Vehicle vehicle;
}
