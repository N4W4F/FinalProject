package com.example.heavylogistics.Controller;

import com.example.heavylogistics.Model.Vehicle;
import com.example.heavylogistics.Service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;


}
