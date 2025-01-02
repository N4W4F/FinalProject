package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.DTOin.InputDriverRequest;
import com.example.heavylogistics.DTOout.OutputDriverRequest;
import com.example.heavylogistics.Model.Customer;
import com.example.heavylogistics.Model.Driver;
import com.example.heavylogistics.Model.DriverProfile;
import com.example.heavylogistics.Model.DriverRequest;
import com.example.heavylogistics.Repository.CustomerRepository;
import com.example.heavylogistics.Repository.DriverProfileRepository;
import com.example.heavylogistics.Repository.DriverRepository;
import com.example.heavylogistics.Repository.DriverRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverRequestService {


    private final DriverRequestRepository driverRequestRepository;
    private final CustomerRepository customerRepository;
    private final DriverRepository driverRepository;
    private final DriverProfileRepository driverProfileRepository;


    public void sendRequestToDriver(Integer customerId, Integer driverId, InputDriverRequest inputDriverRequest) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw  new ApiException("Customer not found");
        }
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw  new ApiException("Driver not found");
        }
        DriverRequest driverRequest = new DriverRequest();
        driverRequest.setId(null);
        driverRequest.setDriverRequestStatus("PENDING");
        driverRequest.setVehicleName(inputDriverRequest.getVehicleName());
        driverRequest.setVehicleType(inputDriverRequest.getVehicleType());
        driverRequest.setVehicleCapacity(inputDriverRequest.getVehicleCapacity());
        driverRequest.setStartLocation(inputDriverRequest.getStartLocation());
        driverRequest.setDestinationLocation(inputDriverRequest.getDestinationLocation());
        driverRequest.setStartTime(inputDriverRequest.getStartTime());
        driverRequest.setEndTime(inputDriverRequest.getEndTime());
        driverRequest.setTotalAmount(calculateTotalAmount(inputDriverRequest.getStartTime(),inputDriverRequest.getEndTime(),driverId));
        driverRequest.setCustomerNote(inputDriverRequest.getCustomerNote());

        driverRequest.setCustomer(customer);
        driverRequest.setDriver(driver);
        driverRequestRepository.save(driverRequest);

    }

    public Double calculateTotalAmount(LocalDateTime start, LocalDateTime end, Integer driverId) {
        DriverProfile profile = driverProfileRepository.findByDriverId(driverId);
        if (profile == null) {
            throw  new ApiException("Driver not found");
        }

        Duration duration = Duration.between(start, end);

        Double days = (double) duration.toDays();
        Double hours = (double) duration.minusDays(days.longValue()).toHours();

        Double totalAmount = 0.0;
        if (days > 0) {
            totalAmount += profile.getPricePerDay() * days;
        }
        if (hours > 0) {
            totalAmount += profile.getPricePerHour() * hours;
        }

        return totalAmount;

    }

    public void driverDecision(Integer driverId, Integer driverRequestId, String decision) {
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw  new ApiException("Driver not found");
        }
        DriverRequest driverRequest = driverRequestRepository.findDriverRequestById(driverRequestId);
        if (driverRequest == null) {
            throw  new ApiException("The request not found");
        }
        if (!driverRequest.getDriverRequestStatus().equals("PENDING")){
            throw  new ApiException("The driver status is not PENDING");
        }
        if (decision.equalsIgnoreCase("approve")) {
            driverRequest.setDriverRequestStatus("APPROVED");
        }else if (decision.equalsIgnoreCase("reject")) {
            driverRequest.setDriverRequestStatus("REJECTED");
        }
        driverRequestRepository.save(driverRequest);
    }


    public List<OutputDriverRequest> getAllDriverRequest(){

        List<DriverRequest> requests = driverRequestRepository.findAll();
        List<OutputDriverRequest> driverRequests = new ArrayList<>();

        for(DriverRequest request : requests){
            OutputDriverRequest outputDriverRequest = convertToOutputDriverRequest(request);
            driverRequests.add(outputDriverRequest);
        }
        return driverRequests;
    }

    public OutputDriverRequest getDriverRequestDetails(Integer driverRequestId){
        DriverRequest driverRequest = driverRequestRepository.findDriverRequestById(driverRequestId);
        if(driverRequest == null){
            throw new ApiException("The request not found. ");
        }
        return convertToOutputDriverRequest(driverRequest);
    }


    public OutputDriverRequest convertToOutputDriverRequest(DriverRequest driverRequest) {
        Customer customer = customerRepository.findCustomerById(driverRequest.getCustomer().getId());

        OutputDriverRequest output = new OutputDriverRequest();
        output.setRequestId(driverRequest.getId());
        output.setRequestStatus(driverRequest.getDriverRequestStatus());
        output.setCustomerName(customer.getName());
        output.setCustomerPhoneNumber(customer.getUser().getPhoneNumber());
        output.setVehicleName(driverRequest.getVehicleName());
        output.setVehicleType(driverRequest.getVehicleType());
        output.setVehicleCapacity(driverRequest.getVehicleCapacity());
        output.setStartLocation(driverRequest.getStartLocation());
        output.setEndLocation(driverRequest.getDestinationLocation());
        output.setStartTime(driverRequest.getStartTime());
        output.setEndTime(driverRequest.getEndTime());
        output.setCustomerNote(driverRequest.getCustomerNote());

        return output;
    }




}
