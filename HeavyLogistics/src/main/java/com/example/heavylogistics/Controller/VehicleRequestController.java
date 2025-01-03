package com.example.heavylogistics.Controller;

import com.example.heavylogistics.DTOin.InputVehicleRequest;
import com.example.heavylogistics.Model.VehicleRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.heavylogistics.ApiResponse.ApiResponse;
import com.example.heavylogistics.Service.VehicleRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Request-vehicle")
@RequiredArgsConstructor

public class VehicleRequestController {

    private final VehicleRequestService vehicleRequestService;

    // Get all vehicle requests (Admin only)
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<VehicleRequest>> getAllVehicleRequests(@PathVariable Integer adminId) {
        List<VehicleRequest> requests = vehicleRequestService.getAllVehicleRequests(adminId);
        return ResponseEntity.status(200).body(requests);
    }


    @PostMapping("/requestVehicleWithoutDriver/{customerId}/{vehicleId}")
    public ResponseEntity requestVehicleWithoutDriver(@PathVariable Integer customerId, @PathVariable Integer vehicleId, @RequestBody @Valid InputVehicleRequest inputVehicleRequest) {
        vehicleRequestService.requestVehicleWithoutDriver(customerId, vehicleId, inputVehicleRequest);
        return ResponseEntity.status(200).body(new ApiResponse("Vehicle request submitted successfully without a driver"));
    }



    // Get customer-specific requests
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<VehicleRequest>> getCustomerRequests(@PathVariable Integer customerId) {
        List<VehicleRequest> requests = vehicleRequestService.getCustomerRequests(customerId);
        return ResponseEntity.status(200).body(requests);
    }
}
