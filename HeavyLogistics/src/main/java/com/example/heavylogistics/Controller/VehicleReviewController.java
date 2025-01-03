package com.example.heavylogistics.Controller;


import com.example.heavylogistics.ApiResponse.ApiResponse;
import com.example.heavylogistics.DTOin.VehicleReviewDTOin;
import com.example.heavylogistics.DTOout.OutputVehicleReviewDTO;
import com.example.heavylogistics.Service.VehicleReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle-Review")
@RequiredArgsConstructor
public class VehicleReviewController {

    private final VehicleReviewService vehicleReviewService;




}
