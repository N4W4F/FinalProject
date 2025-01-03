package com.example.heavylogistics.Repository;

import com.example.heavylogistics.Model.Customer;
import com.example.heavylogistics.Model.DriverRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRequestRepository extends JpaRepository<DriverRequest,Integer> {


    DriverRequest findByDriverId(int id);

    DriverRequest findDriverRequestById(Integer id);

    DriverRequest findDriverRequestByIdAndCustomerAndDriverId(Integer id, Customer customer, Integer driverId);

    boolean existsByCustomerIdAndDriverIdAndDriverRequestStatus(Integer customerId, Integer driverId, String status);

}
