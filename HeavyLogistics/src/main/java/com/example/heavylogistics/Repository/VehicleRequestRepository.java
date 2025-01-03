package com.example.heavylogistics.Repository;

import com.example.heavylogistics.Model.Customer;
import com.example.heavylogistics.Model.Vehicle;
import com.example.heavylogistics.Model.VehicleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleRequestRepository extends JpaRepository<VehicleRequest,Integer> {


    VehicleRequest findVehicleRequestById(Integer id);

    List<VehicleRequest> findByCustomer(Customer customer);

    List<VehicleRequest> findByVehicle(Vehicle vehicle);
}
