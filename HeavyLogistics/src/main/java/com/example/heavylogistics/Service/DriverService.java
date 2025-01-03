package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.DTOin.InputDriver;
import com.example.heavylogistics.Model.Driver;
import com.example.heavylogistics.Model.DriverProfile;
import com.example.heavylogistics.Model.MyUser;
import com.example.heavylogistics.Repository.AuthRepository;
import com.example.heavylogistics.Repository.DriverProfileRepository;
import com.example.heavylogistics.Repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final AuthRepository authRepository;
    private final DriverProfileRepository driverProfileRepository;


    public void driverRegister(InputDriver inputDriver) {
        //User fields
        MyUser user = new MyUser();
        user.setRole("DRIVER");
        user.setUsername(inputDriver.getUsername());
        user.setPassword(inputDriver.getPassword());
        user.setNationalId(inputDriver.getNationalId());
        user.setEmail(inputDriver.getEmail());
        user.setPhoneNumber(inputDriver.getPhoneNumber());
        user.setRegistrationAt(LocalDate.now());

        //driver's fields
        Driver driver = new Driver();
        driver.setId(null);
        driver.setDriverName(inputDriver.getName());
        driver.setNationality(inputDriver.getNationality());
        driver.setNationalIdSource(inputDriver.getNationalIdSource());
        driver.setNationalIdIssueDate(inputDriver.getNationalIdIssueDate());
        driver.setLicenseNumber(inputDriver.getLicenseNumber());
        driver.setLicenseType(inputDriver.getLicenseType());
        driver.setLicenseSource(inputDriver.getLicenseSource());
        driver.setLicenseExpirationDate(inputDriver.getLicenseExpirationDate());
        driver.setDriverProfile(null);
        driver.setUser(user);
        driverRepository.save(driver);
        authRepository.save(user);

    }

    public void updateDriver(Integer driverId, InputDriver inputDriver) {
        // Find the driver by ID
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null){
            throw new ApiException("Error: Driver not found");
        }
        // Update driver's fields
        driver.setDriverName(inputDriver.getName());
        driver.setNationalIdIssueDate(inputDriver.getNationalIdIssueDate());
        driver.setLicenseNumber(inputDriver.getLicenseNumber());
        driver.setLicenseSource(inputDriver.getLicenseSource());
        driver.setLicenseExpirationDate(inputDriver.getLicenseExpirationDate());

        // Update associated user fields
        MyUser user = driver.getUser();
        user.setUsername(inputDriver.getUsername());
        user.setPassword(inputDriver.getPassword());
        user.setEmail(inputDriver.getEmail());
        user.setPhoneNumber(inputDriver.getPhoneNumber());

        // Save updated entities
        driverRepository.save(driver);
        authRepository.save(user);
    }


    public void deleteDriver(Integer driverId) {
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null){
            throw new ApiException("Error: Driver not found");
        }
        // Delete the associated profile
        driverProfileRepository.delete(driver.getDriverProfile());

        // Delete the associated user
        authRepository.delete(driver.getUser());

        // Delete the driver
        driverRepository.delete(driver);
    }



}
