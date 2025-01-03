package com.example.heavylogistics.Controller;

import com.example.heavylogistics.ApiResponse.ApiResponse;
import com.example.heavylogistics.DTOout.OutputVehicleDTO;
import com.example.heavylogistics.Model.Vehicle;
import com.example.heavylogistics.Service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    // get admin
    @GetMapping("/admin/details")
    public ResponseEntity<List<Vehicle>> getDetailsAllVehicle() {
        List<Vehicle> vehicles = vehicleService.getDetailsAllVehicle();
        return ResponseEntity.status(200).body(vehicles);
    }

    // get CUSTOMER
    @GetMapping("/customer")
    public ResponseEntity<List<OutputVehicleDTO>> getAllVehicle() {
        List<OutputVehicleDTO> outputVehicleDTO = vehicleService.getAllVehicle();
        return ResponseEntity.status(200).body(outputVehicleDTO);
    }

    // get LESSOR
    @GetMapping("/lessor/{id}")
    public ResponseEntity<List<Vehicle>> getVehiclesByLessor(@PathVariable Integer id) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByLessor(id);
        return ResponseEntity.status(200).body(vehicles);
    }


    @PostMapping("/addVehicle")
    public ResponseEntity addVehicle(@RequestBody @Valid Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.status(200).body(new ApiResponse("Vehicle added successfully"));
    }

    @PutMapping("/updateVehicle/{id}")
    public ResponseEntity updateVehicle(@PathVariable Integer id, @RequestBody @Valid Vehicle vehicle) {
        vehicleService.updateVehicle(id, vehicle);
        return ResponseEntity.status(200).body(new ApiResponse("Vehicle updated successfully"));
    }

    @DeleteMapping("/deleteVehicle/{id}/{lessorId}")
    public ResponseEntity deleteVehicle(@PathVariable Integer id, @PathVariable Integer lessorId) {
        vehicleService.deleteVehicle(id, lessorId);
        return ResponseEntity.status(200).body(new ApiResponse("Vehicle deleted successfully"));
    }




}
