package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.DTOout.OutputVehicleSchedule;
import com.example.heavylogistics.Model.MyUser;
import com.example.heavylogistics.Model.Vehicle;
import com.example.heavylogistics.Model.VehicleSchedule;
import com.example.heavylogistics.Repository.AuthRepository;
import com.example.heavylogistics.Repository.VehicleRepository;
import com.example.heavylogistics.Repository.VehicleScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleScheduleService {
    private final VehicleScheduleRepository vehicleScheduleRepository;
    private final VehicleRepository vehicleRepository;
    private final AuthRepository authRepository;

    public List<VehicleSchedule> getAllVehicleSchedules(Integer adminId) {
        MyUser admin = authRepository.findMyUserById(adminId);
        if (admin == null)
            throw new ApiException("Admin with ID: " + adminId + " was not found");

        if (admin.getRole().equals("ADMIN"))
            return vehicleScheduleRepository.findAll();

        throw new ApiException("You don't have the permission to access this endpoint");
    }

    public void addVehicleSchedule(Integer authId, Integer vehicleId, VehicleSchedule vehicleSchedule) {
        MyUser auth = authRepository.findMyUserById(authId);
        if (auth == null)
            throw new ApiException("Lessor or Admin with ID: " + authId + " was not found");

        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
        if (vehicle == null)
            throw new ApiException("Vehicle with ID: " + vehicleId + " was not found");

        vehicleSchedule.setVehicle(vehicle);
        vehicleScheduleRepository.save(vehicleSchedule);

        vehicle.getVehicleSchedules().add(vehicleSchedule);
        vehicleRepository.save(vehicle);
    }

    public void updateVehicleSchedule(Integer authId, Integer vehicleScheduleId, VehicleSchedule vehicleSchedule) {
        MyUser auth = authRepository.findMyUserById(authId);
        if (auth == null)
            throw new ApiException("Lessor or Admin with ID: " + authId + " was not found");

        VehicleSchedule oldVehicleSchedule = vehicleScheduleRepository.findVehicleScheduleById(vehicleScheduleId);
        if (oldVehicleSchedule == null)
            throw new ApiException("Vehicle Schedule with ID: " + vehicleScheduleId + " was not found");

        if (oldVehicleSchedule.getVehicle().getLessor().getId().equals(authId) || auth.getRole().equals("ADMIN")) {
            oldVehicleSchedule.setStartTime(vehicleSchedule.getStartTime());
            oldVehicleSchedule.setEndTime(vehicleSchedule.getEndTime());
            oldVehicleSchedule.setAvailabilityStatus(vehicleSchedule.getAvailabilityStatus());
            vehicleScheduleRepository.save(oldVehicleSchedule);
        } else throw new ApiException("You don't have access to update on this vehicle schedule");
    }

    public void deleteVehicleSchedule(Integer authId, Integer vehicleScheduleId) {
        MyUser auth = authRepository.findMyUserById(authId);
        if (auth == null)
            throw new ApiException("Lessor or Admin with ID: " + authId + " was not found");

        VehicleSchedule oldVehicleSchedule = vehicleScheduleRepository.findVehicleScheduleById(vehicleScheduleId);
        if (oldVehicleSchedule == null)
            throw new ApiException("Vehicle Schedule with ID: " + vehicleScheduleId + " was not found");

        if (oldVehicleSchedule.getVehicle().getLessor().getId().equals(authId) || auth.getRole().equals("ADMIN")) {
            vehicleScheduleRepository.delete(oldVehicleSchedule);
        } else throw new ApiException("You don't have access to update on this vehicle schedule");
    }

    public List<OutputVehicleSchedule> getVehicleSchedulesByVehicle(Integer vehicleId) {
        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
        if (vehicle == null)
            throw new ApiException("Vehicle with ID: " + vehicleId + " was not found");

        List<VehicleSchedule> vehicleSchedules = vehicleScheduleRepository.findVehicleSchedulesByVehicle(vehicle);
        if (vehicleSchedules.isEmpty())
            throw new ApiException("This vehicle has no availability schedule yet");

        List<OutputVehicleSchedule> outputVehicleSchedules = new ArrayList<>();
        for (VehicleSchedule vs : vehicleSchedules) {
            outputVehicleSchedules.add(new OutputVehicleSchedule(vs.getStartTime(), vs.getEndTime(), vs.getAvailabilityStatus()));
        }
        return outputVehicleSchedules;
    }
}
