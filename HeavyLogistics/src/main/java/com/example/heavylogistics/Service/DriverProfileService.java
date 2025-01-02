package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.Model.Driver;
import com.example.heavylogistics.Model.DriverProfile;
import com.example.heavylogistics.OutputDTO.OutputDriverProfile;
import com.example.heavylogistics.Repository.DriverProfileRepository;
import com.example.heavylogistics.Repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverProfileService {

    private final DriverProfileRepository driverProfileRepository;
    private final DriverRepository driverRepository;

    public void createDriverProfile(Integer driverId, DriverProfile driverProfile) {
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw new ApiException("Driver not found");
        }
        if (driver.getDriverProfile() != null) {
            throw new ApiException("you already have a profile.");
        }

        DriverProfile profile = new DriverProfile();
        profile.setBioDescription(driverProfile.getBioDescription());
        profile.setYearsOfExperience(driverProfile.getYearsOfExperience());
        profile.setPricePerHour(driverProfile.getPricePerHour());
        profile.setPricePerDay(driverProfile.getPricePerDay());
        profile.setRegion(driverProfile.getRegion());
        profile.setIsAvailable(false);
        profile.setDriver(driver);

        driverProfileRepository.save(profile);

        driver.setDriverProfile(profile);
        driverRepository.save(driver);
    }

    public void updateDriverProfile(Integer driverId, DriverProfile driverProfile) {
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw new ApiException("Driver not found");
        }
        DriverProfile profile = driver.getDriverProfile();
        if (profile == null) {
            throw new RuntimeException("You does not have a profile to update.");
        }

        profile.setBioDescription(driverProfile.getBioDescription());
        profile.setYearsOfExperience(driverProfile.getYearsOfExperience());
        profile.setPricePerHour(driverProfile.getPricePerHour());
        profile.setPricePerDay(driverProfile.getPricePerDay());
        profile.setRegion(driverProfile.getRegion());
        profile.setIsAvailable(false);

        driverProfileRepository.save(profile);
    }

    public void deactivateDriverProfile(Integer driverId) {
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw new ApiException("Driver not found");
        }
        DriverProfile profile = driver.getDriverProfile();
        if (profile == null) {
            throw new RuntimeException("You does not have a profile to deactivate.");
        }
        if (!profile.getIsAvailable()) {
            throw new ApiException("You are not available");
        }
        profile.setIsAvailable(false);
    }

    public List<OutputDriverProfile> getAllDriverProfiles() {
        List<DriverProfile> profiles = driverProfileRepository.findAll();

        List<OutputDriverProfile> outputDriverProfiles = new ArrayList<>();
        for (DriverProfile profile : profiles) {
            OutputDriverProfile outputDriverProfile = convertToOutputDriverProfile(profile);
            outputDriverProfiles.add(outputDriverProfile);
        }
        return outputDriverProfiles;
    }

    public OutputDriverProfile getDriverProfile(Integer profileId) {
        DriverProfile profile = driverProfileRepository.findByDriverId(profileId);
        if (profile == null) {
            throw new RuntimeException("Profile not found");
        }
        return convertToOutputDriverProfile(profile);
    }

    public OutputDriverProfile convertToOutputDriverProfile(DriverProfile profile) {
        OutputDriverProfile output = new OutputDriverProfile();
        output.setName(profile.getDriver().getDriverName());
        output.setBioDescription(profile.getBioDescription());
        output.setYearsOfExperience(profile.getYearsOfExperience());
        output.setPricePerHour(profile.getPricePerHour());
        output.setPricePerDay(profile.getPricePerDay());
        output.setRegion(profile.getRegion());
        output.setIsAvailable(profile.getIsAvailable());
        return output;
    }
}
