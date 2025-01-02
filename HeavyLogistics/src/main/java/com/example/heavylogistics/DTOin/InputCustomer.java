package com.example.heavylogistics.DTOin;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputCustomer {
    private Integer id;

    @NotEmpty(message = "Customer Username cannot be empty")
    @Size(min = 4, max = 10, message = "Customer Username must be between 4 and 10 characters")
    private String username;

    @NotEmpty(message = "Customer National ID cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Customer National ID must be exactly 10 digits.")
    private String nationalId;

    @NotEmpty(message = "Customer Password cannot be empty")
    @Size(min = 6, max = 255, message = "Customer Password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "Customer Email cannot be empty")
    @Email(message = "Customer Email must be a valid email format")
    private String email;

    @NotEmpty(message = "Customer Phone Number cannot be empty")
    @Pattern(regexp = "^05\\d{8}$",
            message = "Customer Phone Number must start with '05' and be exactly 10 digits long.")
    private String phoneNumber;

    @NotEmpty(message = "Customer Name cannot be empty")
    @Size(min = 2, max = 20, message = "Customer Name must be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "Customer Address cannot be empty")
    @Size(min = 2, max = 50, message = "Customer Address must be between 2 and 50 characters")
    private String address;
}
