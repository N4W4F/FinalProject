package com.example.heavylogistics.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lessor {
    @Id
    private Integer id;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    @NotEmpty(message = "Company Name cannot be empty")
    @Size(min = 2, max = 50, message = "Company Name must be between 2 and 50 characters")
    private String companyName;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    @NotEmpty(message = "Company Commercial Register cannot be empty")
    @Pattern(regexp = "^7\\d{9}$", message = "Commercial Register must be exactly 10 digits and start with 7")
    private String commercialRegister;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    @NotEmpty(message = "Company Location cannot be empty")
    @Size(min = 2, max = 50, message = "Company Location must be between 2 and 50 characters")
    private String companyLocation;

    @Column(columnDefinition = "VARCHAR(255)")
    @Size(max = 255, message = "Company Bio must not exceed 255 characters")
    private String bio;

    // Relations
    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lessor")
    private Set<Vehicle> vehicles;
}
