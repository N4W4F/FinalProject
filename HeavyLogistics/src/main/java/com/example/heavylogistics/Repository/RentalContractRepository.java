package com.example.heavylogistics.Repository;

import com.example.heavylogistics.Model.RentalContract;
import com.example.heavylogistics.Model.VehicleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalContractRepository extends JpaRepository<RentalContract, Integer> {
    RentalContract findRentalContractById(Integer id);

    List<RentalContract> findRentalContractByVehicleRequest(VehicleRequest vehicleRequest);
}
