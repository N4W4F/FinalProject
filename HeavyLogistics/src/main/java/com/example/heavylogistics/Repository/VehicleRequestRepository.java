package com.example.heavylogistics.Repository;

import com.example.heavylogistics.Model.VehicleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface VehicleRequestRepository extends JpaRepository<VehicleRequest,Integer> {

    VehicleRequest findVehicleRequestById(Integer id);
}
