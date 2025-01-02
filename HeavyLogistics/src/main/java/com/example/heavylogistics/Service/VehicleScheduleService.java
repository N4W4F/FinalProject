package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.DTOout.OutputVehicleSchedule;
import com.example.heavylogistics.Model.Vehicle;
import com.example.heavylogistics.Model.VehicleSchedule;
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

    public void addVehicleSchedule(Integer vehicleId, VehicleSchedule vehicleSchedule) {
        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
        if (vehicle == null)
            throw new ApiException("Vehicle with ID: " + vehicleId + " was not found");

        vehicleSchedule.setVehicle(vehicle);
        vehicleScheduleRepository.save(vehicleSchedule);

        vehicle.getVehicleSchedules().add(vehicleSchedule);
        vehicleRepository.save(vehicle);
    }

    public void updateVehicleSchedule(Integer vehicleScheduleId, VehicleSchedule vehicleSchedule) {
        VehicleSchedule oldVehicleSchedule = vehicleScheduleRepository.findVehicleScheduleById(vehicleScheduleId);
        if (oldVehicleSchedule == null)
            throw new ApiException("Vehicle Schedule with ID: " + vehicleScheduleId + " was not found");

        oldVehicleSchedule.setStartTime(vehicleSchedule.getStartTime());
        oldVehicleSchedule.set
    }
}
