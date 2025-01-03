package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.DTOin.InputVehicleRequest;
import com.example.heavylogistics.DTOout.OutputVehicleDTO;
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

import java.time.Duration;
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

    // Request Vehicle Without Driver
    // Endpoint

    public void requestVehicleWithoutDriver(Integer customerId, Integer vehicleId, InputVehicleRequest inputVehicleRequest){

        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer with ID: " + customerId + " was not found");
        }

        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
        if (vehicle == null) {
            throw new ApiException("Vehicle with ID: " + vehicleId + " was not found");
        }

        VehicleRequest vehicleRequest = new VehicleRequest();
        vehicleRequest.setCustomer(customer);
        vehicleRequest.setVehicle(vehicle);
        vehicleRequest.setRequestDate(LocalDateTime.now());
        vehicleRequest.setRequestStatus("PENDING");
        vehicleRequest.setWithDrive(false);
        vehicleRequest.setStartTime(inputVehicleRequest.getStartTime());
        vehicleRequest.setEndTime(inputVehicleRequest.getEndTime());

        Double totalVehicleAmount = calculateVehicleAmount(inputVehicleRequest.getStartTime(), inputVehicleRequest.getEndTime(), vehicle);
        vehicleRequest.setTotalAmountVehicle(totalVehicleAmount);
        vehicleRequest.setTotalAmountDriver(0.0);
        vehicleRequestRepository.save(vehicleRequest);


    }

    public Double calculateVehicleAmount(LocalDateTime start, LocalDateTime end, Vehicle vehicle) {
        Duration duration = Duration.between(start, end);

        Double days = (double) duration.toDays();
        Double hours = (double) duration.minusDays(days.longValue()).toHours();

        Double totalAmount = 0.0;
        if (days > 0) {
            totalAmount += vehicle.getPricePerDay() * days;
        }
        if (hours > 0) {
            totalAmount += vehicle.getPricePerHour() * hours;
        }
        return totalAmount;
    }



    // Get customer-specific vehicle requests
    // Endpoint
    public List<VehicleRequest> getCustomerRequests(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer with ID: " + customerId + " was not found");
        }

        return vehicleRequestRepository.findByCustomer(customer);
    }

    // Endpoint
    /*
    private OutputVehicleDTO getVehicleById(Integer vehicleId) {

        Vehicle vehicle = g
        for (VehicleRequest request : requests) {
            if ("APPROVED".equalsIgnoreCase(request.getRequestStatus())) {
                return false;
            }
        }
        return true;
    }

     */
}
