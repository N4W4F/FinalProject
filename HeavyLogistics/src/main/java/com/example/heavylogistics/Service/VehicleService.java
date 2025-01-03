package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.DTOout.OutputVehicleDTO;
import com.example.heavylogistics.Model.Lessor;
import com.example.heavylogistics.Model.MyUser;
import com.example.heavylogistics.Model.Vehicle;
import com.example.heavylogistics.Repository.AuthRepository;
import com.example.heavylogistics.Repository.LessorRepository;
import com.example.heavylogistics.Repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final AuthRepository authRepository;
    private final LessorRepository lessorRepository;

    // get admin
    public List<Vehicle> getDetailsAllVehicle() {
        return vehicleRepository.findAll();
    }

    // get CUSTOMER
    // Endpoint
    public List<OutputVehicleDTO> getAllVehicle() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<OutputVehicleDTO> outputVehicleDTO = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            OutputVehicleDTO dto = new OutputVehicleDTO();
            dto.setVehicleName(vehicle.getVehicleName());
            dto.setVehicleType(vehicle.getVehicleType());
            dto.setPricePerDay(vehicle.getPricePerDay());
            dto.setPricePerHour(vehicle.getPricePerHour());
            dto.setCapacity(vehicle.getCapacity());
            dto.setColor(vehicle.getColor());
            dto.setLocation(vehicle.getLocation());
            outputVehicleDTO.add(dto);
        }

        return outputVehicleDTO;
    }

    // get LESSOR
    //Endpoint
    public List<Vehicle> getVehiclesByLessor(Integer lessorId) {
        MyUser lessorUser = authRepository.findMyUserById(lessorId);
        if (lessorUser == null || !lessorUser.getRole().equalsIgnoreCase("LESSOR")) {
            throw new ApiException("Lessor with ID: " + lessorId + " not found or not authorized.");
        }

        Lessor lessor = lessorUser.getLessor();
        return new ArrayList<>(lessor.getVehicles());
    }


    public void addVehicle(Integer lessorId, Vehicle vehicle) {
        Lessor lessor = lessorRepository.findLessorById(lessorId);

        if (lessor == null ) {
            throw new ApiException("Lessor not found. ");
        }

        vehicle.setLessor(lessor);
        vehicleRepository.save(vehicle);
    }


    public void updateVehicle(Integer lessorId,Integer vehicleId, Vehicle updatedVehicle) {
        Lessor lessor = lessorRepository.findLessorById(lessorId);
        if (lessor == null ) {
            throw new ApiException("Lessor not found. ");
        }
        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
        if (vehicle == null) {
            throw new ApiException("Vehicle with ID: " + vehicleId + " not found.");
        }

        if (!vehicle.getLessor().getId().equals(lessor)) {
            throw new ApiException("Unauthorized to update this vehicle.");
        }


        vehicle.setNameVehicleOwner(updatedVehicle.getNameVehicleOwner());
        vehicle.setCardNumber(updatedVehicle.getCardNumber());
        vehicle.setCardIssuanceDate(updatedVehicle.getCardIssuanceDate());
        vehicle.setCardExpiryDate(updatedVehicle.getCardExpiryDate());
        vehicle.setCardRenewalDate(updatedVehicle.getCardRenewalDate());
        vehicle.setVehicleName(updatedVehicle.getVehicleName());
        vehicle.setVehicleType(updatedVehicle.getVehicleType());
        vehicle.setLicensePlate(updatedVehicle.getLicensePlate());
        vehicle.setPricePerDay(updatedVehicle.getPricePerDay());
        vehicle.setPricePerHour(updatedVehicle.getPricePerHour());
        vehicle.setCapacity(updatedVehicle.getCapacity());
        vehicle.setColor(updatedVehicle.getColor());
        vehicle.setLocation(updatedVehicle.getLocation());
        vehicle.setYearOfManufacture(updatedVehicle.getYearOfManufacture());
        vehicle.setDriverPricePerHour(updatedVehicle.getDriverPricePerHour());
        vehicle.setDriverPricePerDay(updatedVehicle.getDriverPricePerDay());
        vehicle.setDriverPricePerDay(updatedVehicle.getDriverPricePerDay());
        vehicle.setDriverPricePerHour(updatedVehicle.getDriverPricePerHour());

        vehicleRepository.save(vehicle);
    }


    public void deleteVehicle(Integer vehicleId, Integer lessorId) {
        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);

        if (vehicle == null) {
            throw new ApiException("Vehicle with ID: " + vehicleId + " not found.");
        }

        if (!vehicle.getLessor().getId().equals(lessorId)) {
            throw new ApiException("Unauthorized to delete this vehicle.");
        }

        vehicleRepository.delete(vehicle);
    }









}
