package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.DTOin.VehicleReviewDTOin;
import com.example.heavylogistics.Model.Customer;
import com.example.heavylogistics.Model.Vehicle;
import com.example.heavylogistics.Model.VehicleReview;
import com.example.heavylogistics.Repository.CustomerRepository;
import com.example.heavylogistics.Repository.VehicleRepository;
import com.example.heavylogistics.Repository.VehicleReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleReviewService {

    private final VehicleReviewRepository vehicleReviewRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;


}
