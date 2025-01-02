package com.example.heavylogistics.Controller;

import com.example.heavylogistics.ApiResponse.ApiResponse;
import com.example.heavylogistics.DTOin.InputCustomer;
import com.example.heavylogistics.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get-all/{id}")
    public ResponseEntity getAllCustomer(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(customerService.getAllCustomers(id));
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid InputCustomer inputCustomer) {
        customerService.register(inputCustomer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer has been registered successfully"));
    }

    @PutMapping("/update/{authId}/{customerId}")
    public ResponseEntity updateCustomer(@PathVariable Integer authId,
                                         @PathVariable Integer customerId,
                                         @RequestBody @Valid InputCustomer inputCustomer) {
        customerService.updateCustomer(authId, customerId, inputCustomer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer with ID: " + customerId + " has been updated successfully"));
    }

    @DeleteMapping("/delete/{authId}/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable Integer authId,
                                         @PathVariable Integer customerId) {
        customerService.deleteCustomer(authId, customerId);
        return ResponseEntity.status(200).body(new ApiResponse("Customer with ID: " + customerId + " has been deleted successfully"));
    }
}
