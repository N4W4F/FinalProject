package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.Model.Customer;
import com.example.heavylogistics.Model.MyUser;
import com.example.heavylogistics.Model.Vehicle;
import com.example.heavylogistics.Model.VehicleRequest;
import com.example.heavylogistics.Repository.AuthRepository;
import com.example.heavylogistics.Repository.CustomerRepository;
import com.example.heavylogistics.Repository.VehicleRepository;
import com.example.heavylogistics.Repository.VehicleRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleRequestService {
    private final VehicleRequestRepository vehicleRequestRepository;
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

//    public List<VehicleRequest> getAllVehicleRequests(Integer adminId) {
//        MyUser admin = authRepository.findMyUserById(adminId);
//        if (admin == null)
//            throw new ApiException("Admin with ID: " + adminId + " was not found");
//
//        return vehicleRequestRepository.findAll();
//    }
//
//    public void requestVehicle(Integer customerId, Integer vehicleId, VehicleRequest vehicleRequest) {
//        Customer customer = customerRepository.findCustomerById(customerId);
//        if (customer == null)
//            throw new ApiException("Customer with ID: " + customerId + " was not found");
//
//        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
//        if (vehicle == null)
//            throw new ApiException("Vehicle with ID: " + vehicleId + " was not found");
//
//        vehicleRequest.setCustomer(customer);
//        vehicleRequest.setVehicle(vehicle);
//        vehicleRequest.
//    }

    // Get all vehicle requests (Admin only)
    public List<VehicleRequest> getAllVehicleRequests(Integer adminId) {
        MyUser admin = authRepository.findMyUserById(adminId);
        if (admin == null || !admin.getRole().equalsIgnoreCase("ADMIN")) {
            throw new ApiException("Admin with ID: " + adminId + " was not found or is not authorized.");
        }

        return vehicleRequestRepository.findAll();
    }

    // Request a vehicle (Customer creates a request)
    public void requestVehicle(Integer customerId, Integer vehicleId, VehicleRequest vehicleRequest) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer with ID: " + customerId + " was not found");
        }

        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
        if (vehicle == null) {
            throw new ApiException("Vehicle with ID: " + vehicleId + " was not found");
        }


        if (!isVehicleAvailable(vehicle)) {
            throw new ApiException("The requested vehicle is not available at the moment");
        }

        vehicleRequest.setCustomer(customer);
        vehicleRequest.setVehicle(vehicle);
        vehicleRequest.setRequestDate(LocalDateTime.now());
        vehicleRequest.setRequestStatus("PENDING");
        vehicleRequestRepository.save(vehicleRequest);
    }

    // Update vehicle request status (Admin only)
    public void updateRequestStatus(Integer adminId, Integer requestId, String status) {
        MyUser admin = authRepository.findMyUserById(adminId);
        if (admin == null || !admin.getRole().equalsIgnoreCase("ADMIN")) {
            throw new ApiException("Admin with ID: " + adminId + " was not found or is not authorized.");
        }

        VehicleRequest vehicleRequest = vehicleRequestRepository.findById(requestId).orElse(null);
        if (vehicleRequest == null) {
            throw new ApiException("Vehicle request with ID: " + requestId + " was not found");
        }

        if (!status.equalsIgnoreCase("APPROVED") && !status.equalsIgnoreCase("REJECTED") && !status.equalsIgnoreCase("COMPLETED")&& !status.equalsIgnoreCase("CANCELLED")) {
            throw new ApiException("Invalid status. Allowed statuses: APPROVED, REJECTED,COMPLETED,CANCELLED.");
        }

        vehicleRequest.setRequestStatus(status.toUpperCase());
        vehicleRequestRepository.save(vehicleRequest);
    }


    // Get customer-specific vehicle requests
    public List<VehicleRequest> getCustomerRequests(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer with ID: " + customerId + " was not found");
        }

        return vehicleRequestRepository.findByCustomer(customer);
    }


    private boolean isVehicleAvailable(Vehicle vehicle) {
        List<VehicleRequest> requests = vehicleRequestRepository.findByVehicle(vehicle);
        for (VehicleRequest request : requests) {
            if ("APPROVED".equalsIgnoreCase(request.getRequestStatus())) {
                return false;
            }
        }
        return true;
    }
}
