package com.example.heavylogistics.Controller;

import com.example.heavylogistics.ApiResponse.ApiResponse;
import com.example.heavylogistics.DTOout.OutputDriverProfile;
import com.example.heavylogistics.Model.DriverProfile;
import com.example.heavylogistics.Service.DriverProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/heavy-logistics/driver")
public class DriverProfileController {

    private final DriverProfileService driverProfileService;

    @PostMapping("/create-profile/{driverId}")
    public ResponseEntity<?> createProfile(@PathVariable Integer driverId, @RequestBody@Valid DriverProfile driverProfile) {
        driverProfileService.createDriverProfile(driverId, driverProfile);
        return ResponseEntity.status(200).body(new ApiResponse("Driver profile created successfully."));
    }

    @PutMapping("/update-profile/{driverId}")
    public ResponseEntity<?> updateProfile(@PathVariable Integer driverId, @RequestBody@Valid DriverProfile driverProfile) {
        driverProfileService.updateDriverProfile(driverId, driverProfile);
        return ResponseEntity.status(200).body(new ApiResponse("Driver profile updated successfully."));
    }

    //endpoint
    @PutMapping("/deactivate-profile/{profileId}")
    public ResponseEntity<?> deactivateProfile(@PathVariable Integer profileId) {
        driverProfileService.deactivateDriverProfile(profileId);
        return ResponseEntity.status(200).body(new ApiResponse("Driver profile deactivated successfully"));
    }


    //endpoint
    @GetMapping("/profiles")
    public ResponseEntity<?> getAllProfiles() {
        List<OutputDriverProfile> profiles = driverProfileService.getAllDriverProfiles();
        return ResponseEntity.status(200).body(profiles);
    }

    //endpoint
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<?> getProfile(@PathVariable Integer profileId) {
        OutputDriverProfile profile = driverProfileService.getDriverProfile(profileId);
        return ResponseEntity.status(200).body(profile);
    }

}
