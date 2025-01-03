package com.example.heavylogistics.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyUser /*implements UserDetails*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNIQUE")
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, max = 10, message = "Username must be between 4 and 10 characters")
    private String username;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNIQUE")
    @NotEmpty(message = "User National ID cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "National ID must be exactly 10 digits.")
    private String nationalId;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    @NotEmpty(message = "User Password cannot be empty")
    @Size(min = 6, max = 255, message = "User Password must be at least 6 characters")
    private String password;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL UNIQUE")
    @NotEmpty(message = "User Email cannot be empty")
    @Email(message = "User Email must be a valid email format")
    private String email;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNIQUE")
    @NotEmpty(message = "Customer Phone Number cannot be empty")
    @Pattern(regexp = "^05\\d{8}$",
            message = "User Phone Number must start with '05' and be exactly 10 digits long.")
    private String phoneNumber;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    @Pattern(regexp = "^(CUSTOMER|LESSOR|DRIVER|ADMIN)$",
            message = "User Role must be either 'CUSTOMER', 'LESSOR', 'DRIVER', or 'ADMIN' only")
    private String role;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @NotNull(message = "Registration Date cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationAt = LocalDate.now();


    // Relations
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Lessor lessor;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Driver driver;

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }*/
}
