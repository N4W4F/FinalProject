package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.Model.MyUser;
import com.example.heavylogistics.Model.RentalContract;
import com.example.heavylogistics.Model.VehicleRequest;
import com.example.heavylogistics.Repository.AuthRepository;
import com.example.heavylogistics.Repository.RentalContractRepository;
import com.example.heavylogistics.Repository.VehicleRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalContractService {
    private final RentalContractRepository rentalContractRepository;
    private final AuthRepository authRepository;
    private final VehicleRequestRepository vehicleRequestRepository;

    public List<RentalContract> getAllRentalsContracts(Integer adminId) {
        MyUser admin = authRepository.findMyUserById(adminId);
        if (admin == null)
            throw new ApiException("Admin with ID: " + adminId + " was not found");

        if (admin.getRole().equals("ADMIN"))
            return rentalContractRepository.findAll();

        throw new ApiException("You don't have the permission to access this endpoint");
    }

    public void createRentalContract (Integer requestId, RentalContract rentalContract) {
        VehicleRequest vehicleRequest = vehicleRequestRepository.findVehicleRequestById(requestId);
        if (vehicleRequest == null)
            throw new ApiException("Vehicle Request wasn't found");

        if (vehicleRequest.getRequestStatus().equals("APPROVED")) {
            rentalContract.setVehicleRequest(vehicleRequest);
            rentalContractRepository.save(rentalContract);
        }
        throw new ApiException("Vehicle Request needs to be approved by the lessor first");
    }
}

