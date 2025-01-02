package com.example.heavylogistics.Repository;

import com.example.heavylogistics.Model.DriverProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverProfileRepository extends JpaRepository<DriverProfile, Integer> {

    DriverProfile findByDriverId(Integer id);


}
