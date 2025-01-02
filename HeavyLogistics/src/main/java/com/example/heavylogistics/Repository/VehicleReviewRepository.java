package com.example.heavylogistics.Repository;

import com.example.heavylogistics.Model.VehicleReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleReviewRepository  extends JpaRepository<VehicleReview,Integer> {

    VehicleReview findVehicleReviewById(Integer id);
}
