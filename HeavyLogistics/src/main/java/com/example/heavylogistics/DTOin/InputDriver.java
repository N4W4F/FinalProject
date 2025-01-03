package com.example.heavylogistics.DTOin;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InputDriver {

    @NotEmpty(message = "Username is required")
    @Size(min = 4, max = 10, message = "Username must be between 4 and 10 characters")
    private String username;

    @NotEmpty(message = "User Password is required")
    @Size(min = 6, max = 255, message = "User Password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "User Name cannot be empty")
    @Size(min = 2, max = 20, message = "User Name must be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "User Email cannot be empty")
    @Email(message = "User Email must be a valid email format")
    private String email;

    @NotEmpty(message = "PhoneNumber is required")
    @Pattern(regexp = "^\\+9665[0-9]{8}$", message = "Phone number must be a valid Saudi number starting with +9665 and followed by 8 digits")
    private String phoneNumber;

    @Pattern(regexp = "^(CUSTOMER|LESSOR|DRIVER|ADMIN)$",
            message = "User Role must be either 'CUSTOMER', 'LESSOR', 'DRIVER', or 'ADMIN' only")
    private String role;

    private LocalDate registrationAt;

    @NotEmpty(message = "National ID is required.")
    @Pattern(regexp = "^\\d{10}$", message = "ID number must be exactly 10 digits.")
    private String nationalId;

    @NotNull(message = "Nationality is required")
    private String nationality;

    @NotNull(message = "National ID Source is required")
    private String nationalIdSource;

    @NotNull(message = "National ID Issue Date is required")
    private LocalDate nationalIdIssueDate;

    @NotEmpty(message = "License number is required.")
    private String licenseNumber;

    //check type of license
    @NotEmpty(message = "License type is required.")
    @Size(max = 40, message = "License type must not exceed 40 characters.")
    private String licenseType;

    @NotNull(message = "license Source ID Source is required")
    private String licenseSource;

    @NotNull(message = "License expiration date is required.")
    @Future(message = "License expiration date must be a future date.")
    private LocalDate licenseExpirationDate;



}
