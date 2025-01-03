package com.example.heavylogistics.Controller;

import com.example.heavylogistics.ApiResponse.ApiResponse;
import com.example.heavylogistics.Model.VehicleSchedule;
import com.example.heavylogistics.Repository.VehicleRepository;
import com.example.heavylogistics.Service.VehicleScheduleService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicle-schedule")
public class VehicleScheduleController {
    private final VehicleScheduleService vehicleScheduleService;

    @GetMapping("/get-all")
    public ResponseEntity getAllVehicleSchedules(@PathVariable Integer adminId) {
        return ResponseEntity.status(200).body(vehicleScheduleService.getAllVehicleSchedules(adminId));
    }

    @PostMapping("/add/{authId}/{vehicleId}")
    public ResponseEntity addVehicleSchedule(@PathVariable Integer authId,
                                             @PathVariable Integer vehicleId,
                                             @RequestBody @Valid VehicleSchedule vehicleSchedule) {
        vehicleScheduleService.addVehicleSchedule(authId, vehicleId, vehicleSchedule);
        return ResponseEntity.status(200).body(new ApiResponse("Vehicle Schedule has been added successfully"));
    }

    @PutMapping("/update/{authId}/{vehicleScheduleId}")
    public ResponseEntity updateVehicleSchedule(@PathVariable Integer authId,
                                                @PathVariable Integer vehicleScheduleId,
                                                @RequestBody @Valid VehicleSchedule vehicleSchedule) {
        vehicleScheduleService.updateVehicleSchedule(authId, vehicleScheduleId, vehicleSchedule);
        return ResponseEntity.status(200).body(new ApiResponse("Vehicle Schedule has been updated successfully"));
    }

    @DeleteMapping("/delete/{authId}/{vehicleScheduleId}")
    public ResponseEntity deleteVehicleSchedule(@PathVariable Integer authId,
                                                @PathVariable Integer vehicleScheduleId) {
        vehicleScheduleService.deleteVehicleSchedule(authId, vehicleScheduleId);
        return ResponseEntity.status(200).body(new ApiResponse("Vehicle Schedule has been deleted successfully"));
    }

    @GetMapping("/get/by-vehicle/{vehicleId}")
    public ResponseEntity getVehicleSchedulesByVehicle(@PathVariable Integer vehicleId) {
        return ResponseEntity.status(200).body(vehicleScheduleService.getVehicleSchedulesByVehicle(vehicleId));
    }
}
