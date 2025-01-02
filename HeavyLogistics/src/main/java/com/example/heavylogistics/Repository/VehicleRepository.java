package com.example.heavylogistics.Repository;

import com.example.heavylogistics.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VehicleRepository extends JpaRepository<Vehicle,Integer>{

    Vehicle findVehicleById(Integer id);
}
