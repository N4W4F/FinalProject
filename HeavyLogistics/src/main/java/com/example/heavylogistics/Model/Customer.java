package com.example.heavylogistics.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    private Integer id;

    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    @NotEmpty(message = "Customer Name cannot be empty")
    @Size(min = 2, max = 20, message = "User Name must be between 2 and 20 characters")
    private String name;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    @NotEmpty(message = "Customer Address cannot be empty")
    @Size(min = 2, max = 50, message = "Customer Address must be between 2 and 50 characters")
    private String address;

    // Relations
    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;

    // Driver
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnore
    private Set<DriverRequest> driverRequests;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnore
    private Set<DriverReview> driverReviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnore
    private Set<VehicleRequest> vehicleRequests;
}
