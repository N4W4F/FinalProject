package com.example.heavylogistics.Controller;

import com.example.heavylogistics.ApiResponse.ApiResponse;
import com.example.heavylogistics.Model.RentalContract;
import com.example.heavylogistics.Service.RentalContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rental-contract")
public class RentalContractController {
    private final RentalContractService rentalContractService;

    @GetMapping("/get-all/{adminId}")
    public ResponseEntity getAllRentalContracts(@PathVariable Integer adminId) {
        return ResponseEntity.status(200).body(rentalContractService.getAllRentalsContracts(adminId));
    }

    @PostMapping("/create/{requestId}")
    public ResponseEntity createRentalContract(@PathVariable Integer requestId,
                                            @RequestBody @Valid RentalContract rentalContract) {
        rentalContractService.createRentalContract(requestId, rentalContract);
        return ResponseEntity.status(200).body(new ApiResponse("Rental Contract has been created successfully"));
    }
}
