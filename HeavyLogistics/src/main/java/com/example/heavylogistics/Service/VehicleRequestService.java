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

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleRequestService {
    private final VehicleRequestRepository vehicleRequestRepository;
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    public List<VehicleRequest> getAllVehicleRequests(Integer adminId) {
        MyUser admin = authRepository.findMyUserById(adminId);
        if (admin == null)
            throw new ApiException("Admin with ID: " + adminId + " was not found");

        return vehicleRequestRepository.findAll();
    }

    public void requestVehicle(Integer customerId, Integer vehicleId, VehicleRequest vehicleRequest) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null)
            throw new ApiException("Customer with ID: " + customerId + " was not found");

        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
        if (vehicle == null)
            throw new ApiException("Vehicle with ID: " + vehicleId + " was not found");

        vehicleRequest.setCustomer(customer);
        vehicleRequest.setVehicle(vehicle);
        vehicleRequest.set
    }
}
