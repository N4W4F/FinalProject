package com.example.heavylogistics.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentalContract {
    @Id
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    @NotEmpty(message = "Rental Contract Description cannot be empty")
    @Size(min = 2, max = 255, message = "Rental Contract Description must be between 2 and 255 characters")
    private String description;

    // Relations
    @OneToOne
    @MapsId
    @JsonIgnore
    private VehicleRequest vehicleRequest;
}
