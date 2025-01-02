package com.example.heavylogistics.DTOin;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputLessor {
    private Integer id;

    @NotEmpty(message = "Lessor Username cannot be empty")
    @Size(min = 4, max = 10, message = "Lessor Username must be between 4 and 10 characters")
    private String username;

    @NotEmpty(message = "Lessor National ID cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Lessor National ID must be exactly 10 digits.")
    private String nationalId;

    @NotEmpty(message = "Lessor Password cannot be empty")
    @Size(min = 6, max = 255, message = "Lessor Password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "Lessor Email cannot be empty")
    @Email(message = "Lessors Email must be a valid email format")
    private String email;

    @NotEmpty(message = "Lessor Phone Number cannot be empty")
    @Pattern(regexp = "^05\\d{8}$",
            message = "Lessor Phone Number must start with '05' and be exactly 10 digits long.")
    private String phoneNumber;

    @NotEmpty(message = "Company Name cannot be empty")
    @Size(min = 2, max = 50, message = "Company Name must be between 2 and 50 characters")
    private String companyName;

    @NotEmpty(message = "Company Commercial Register cannot be empty")
    @Pattern(regexp = "^7\\d{9}$", message = "Commercial Register must be exactly 10 digits and start with 7")
    private String commercialRegister;

    @NotEmpty(message = "Company Location cannot be empty")
    @Size(min = 2, max = 50, message = "Company Location must be between 2 and 50 characters")
    private String companyLocation;
}
