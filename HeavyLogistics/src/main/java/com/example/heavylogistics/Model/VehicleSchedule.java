package com.example.heavylogistics.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Date cannot be null")
    @Pattern(regexp ="^(0?[1-9]|1[012])\\/(0?[1-9]|[12][0-9]|3[01])\\/([0-9]{4})$" )
    @Column
    private LocalDate startTime;

    @NotNull(message = "Date cannot be null")
    @Pattern(regexp ="^(0?[1-9]|1[012])\\/(0?[1-9]|[12][0-9]|3[01])\\/([0-9]{4})$" )
    @Column
    private LocalDate endTime;

    @Column
    private Boolean availabilityStatus;

    // Relations

    @ManyToOne
    @JsonIgnore
    private Vehicle vehicle;
}
