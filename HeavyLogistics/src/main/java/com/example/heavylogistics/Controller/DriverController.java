package com.example.heavylogistics.Controller;

import com.example.heavylogistics.ApiResponse.ApiResponse;
import com.example.heavylogistics.DTOin.InputDriver;
import com.example.heavylogistics.Service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/heavy-logistics/driver")
public class DriverController {
    private final DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity<?> driverRegister(@RequestBody@Valid InputDriver inputDriver) {
        driverService.driverRegister(inputDriver);
        return ResponseEntity.status(200).body(new ApiResponse("Driver registered successfully"));
    }

    @PutMapping("/update/{driverId}")
    public ResponseEntity<?> updateDriver(@PathVariable Integer driverId, @RequestBody@Valid InputDriver inputDriver) {
        driverService.updateDriver(driverId, inputDriver);
        return ResponseEntity.status(200).body(new ApiResponse("Driver updated successfully"));
    }

    @DeleteMapping("/delete/{driverId}")
    public ResponseEntity<?> deleteDriver(@PathVariable Integer driverId) {
        driverService.deleteDriver(driverId);
        return ResponseEntity.status(200).body(new ApiResponse("Driver deleted successfully"));
    }

}
