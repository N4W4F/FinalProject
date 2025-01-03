package com.example.heavylogistics.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {
    @Id
    private Integer id;



    @Column(columnDefinition = "DECIMAL NOT NULL")
    @NotNull(message = "Negotiation Price cannot be empty")
    @Positive(message = "Negotiation Price number must be positive")
    private Double negotiationPrice;

    @NotEmpty(message = "Request Status should be not empty")
    @Pattern(regexp = "PENDING|APPROVED|REJECTED|COMPLETED|CANACELLED")
    @Column(columnDefinition = "varchar(8) not null")
    private String requestStatus;

    @NotNull(message = "Date cannot be null")
    @Column
    private LocalDateTime requestDate;


    // Relations
    @OneToOne
    @MapsId
    @JsonIgnore
    private Vehicle vehicle;

    @ManyToOne
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vehicleRequest")
    private RentalContract rentalContract;
}
