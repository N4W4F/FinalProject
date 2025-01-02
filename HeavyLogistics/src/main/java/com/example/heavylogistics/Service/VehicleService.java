package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.DTOin.VehicleDTOin;
import com.example.heavylogistics.Model.MyUser;
import com.example.heavylogistics.Model.Vehicle;
import com.example.heavylogistics.Repository.AuthRepository;
import com.example.heavylogistics.Repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final AuthRepository authRepository;


    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public void VehicleRegister(VehicleDTOin vehicleDTOin) {

    MyUser myUser=new MyUser();
    myUser.setNationalId(vehicleDTOin.getNationalId());
    myUser.setRegistrationAt(vehicleDTOin.getRegistrationAt());

    Vehicle vehicle=new Vehicle();
    vehicle.setNameVehicleOwner(vehicleDTOin.getNameVehicleOwner());
    vehicle.setCardNumber(vehicleDTOin.getCardNumber());
    vehicle.setCardIssuanceDate(vehicleDTOin.getCardIssuanceDate());
    vehicle.setCardExpiryDate(vehicleDTOin.getCardExpiryDate());
    vehicle.setCardRenewalDate(vehicleDTOin.getCardRenewalDate());
    vehicle.setVehicleName(vehicleDTOin.getVehicleName());
    vehicle.setVehicleType(vehicleDTOin.getVehicleType());
    vehicle.setVehicleCapacity(vehicleDTOin.getVehicleCapacity());
    vehicle.setLicensePlate(vehicleDTOin.getLicensePlate());
    vehicle.setPricePerDay(vehicleDTOin.getPricePerDay());
    vehicle.setPricePerHour(vehicleDTOin.getPricePerHour());
    vehicle.setLocation(vehicleDTOin.getLocation());
    vehicle.setYearOfManufacture(vehicleDTOin.getYearOfManufacture());
    vehicle.setVehicleColor(vehicleDTOin.getVehicleColor());

    vehicleRepository.save(vehicle);
    authRepository.save(myUser);

    }

    public void updateVehicle(Integer vehicleId,VehicleDTOin vehicleDTOin){

        Vehicle vehicle=vehicleRepository.findVehicleById(vehicleId);

        if(vehicle==null){
            throw new ApiException("Vehicle not found");
        }

        vehicle.setNameVehicleOwner(vehicleDTOin.getNameVehicleOwner());
        vehicle.setCardNumber(vehicleDTOin.getCardNumber());
        vehicle.setCardIssuanceDate(vehicleDTOin.getCardIssuanceDate());
        vehicle.setCardExpiryDate(vehicleDTOin.getCardExpiryDate());
        vehicle.setCardRenewalDate(vehicleDTOin.getCardRenewalDate());
        vehicle.setVehicleName(vehicleDTOin.getVehicleName());
        vehicle.setVehicleType(vehicleDTOin.getVehicleType());
        vehicle.setVehicleCapacity(vehicleDTOin.getVehicleCapacity());
        vehicle.setLicensePlate(vehicleDTOin.getLicensePlate());
        vehicle.setPricePerDay(vehicleDTOin.getPricePerDay());
        vehicle.setPricePerHour(vehicleDTOin.getPricePerHour());
        vehicle.setLocation(vehicleDTOin.getLocation());
        vehicle.setYearOfManufacture(vehicleDTOin.getYearOfManufacture());
        vehicle.setVehicleColor(vehicleDTOin.getVehicleColor());

        MyUser myUser=new MyUser();
        myUser.setNationalId(vehicleDTOin.getNationalId());
        myUser.setRegistrationAt(vehicleDTOin.getRegistrationAt());

        vehicleRepository.save(vehicle);
        authRepository.save(myUser);
    }








}
