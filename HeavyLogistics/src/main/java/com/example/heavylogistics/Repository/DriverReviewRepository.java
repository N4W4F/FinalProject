package com.example.heavylogistics.Repository;

import com.example.heavylogistics.Model.DriverReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverReviewRepository extends JpaRepository<DriverReview,Integer> {


    DriverReview findDriverReviewById(Integer id);

    List<DriverReview> findDriverReviewByDriverId(Integer id);

    List<DriverReview> findByCustomerId(Integer id);

}
