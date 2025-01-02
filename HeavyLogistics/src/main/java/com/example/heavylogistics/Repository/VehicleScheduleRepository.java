package com.example.heavylogistics.Repository;


import com.example.heavylogistics.Model.Vehicle;
import com.example.heavylogistics.Model.VehicleSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleScheduleRepository extends JpaRepository<VehicleSchedule,Integer>{

    VehicleSchedule findVehicleScheduleById(Integer id);

    List<VehicleSchedule> findVehicleSchedulesByVehicle(Vehicle vehicle);
}
