package com.example.heavylogistics.Controller;

import com.example.heavylogistics.ApiResponse.ApiResponse;
import com.example.heavylogistics.DTOin.InputDriverRequest;
import com.example.heavylogistics.DTOout.OutputDriverRequest;
import com.example.heavylogistics.Service.DriverRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/heavy-logistics/driver")
public class DriverRequestController {

    private final DriverRequestService driverRequestService;

    @PostMapping("/{customerId}/{driverId}")
    public ResponseEntity<?> sendRequestToDriver(@PathVariable Integer customerId, @PathVariable Integer driverId, @RequestBody@Valid InputDriverRequest inputDriverRequest) {
        driverRequestService.sendRequestToDriver(customerId, driverId, inputDriverRequest);
        return ResponseEntity.status(200).body(new ApiResponse("Driver request sent successfully."));
    }

    @PutMapping("/driver-decision/{driverId}/{driverRequestId}")
    public ResponseEntity<?> driverDecision(@PathVariable Integer driverId, @PathVariable Integer driverRequestId, @RequestParam String decision) {
        driverRequestService.driverDecision(driverId, driverRequestId, decision);
        return ResponseEntity.status(200).body(new ApiResponse("Driver request decision processed successfully."));
    }

    @GetMapping("/all-request")
    public ResponseEntity<?> getAllDriverRequests() {
        List<OutputDriverRequest> driverRequests = driverRequestService.getAllDriverRequest();
        return ResponseEntity.status(200).body(driverRequests);
    }

    @GetMapping("/request-details/{driverRequestId}")
    public ResponseEntity<?> getDriverRequestDetails(@PathVariable Integer driverRequestId) {
        OutputDriverRequest driverRequest = driverRequestService.getDriverRequestDetails(driverRequestId);
        return ResponseEntity.status(200).body(driverRequest);
    }
}
