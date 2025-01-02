package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.DTOin.InputCustomer;
import com.example.heavylogistics.Model.Customer;
import com.example.heavylogistics.Model.Driver;
import com.example.heavylogistics.Model.DriverRequest;
import com.example.heavylogistics.Model.MyUser;
import com.example.heavylogistics.Repository.AuthRepository;
import com.example.heavylogistics.Repository.CustomerRepository;
import com.example.heavylogistics.Repository.DriverRepository;
import com.example.heavylogistics.Repository.DriverRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;
    private final DriverRequestRepository driverRequestRepository;
    private final DriverRepository driverRepository;

    public List<Customer> getAllCustomers(Integer adminId) {
        MyUser admin = authRepository.findMyUserById(adminId);
        if (admin == null)
            throw new ApiException("Admin was not found");

        if (admin.getRole().equals("ADMIN"))
            return customerRepository.findAll();

        throw new ApiException("You don't have the permission to access this endpoint");
    }

    public void register(InputCustomer inputCustomer) {
        MyUser myUser = new MyUser();
        myUser.setUsername(inputCustomer.getUsername());
        myUser.setNationalId(inputCustomer.getNationalId());
        myUser.setPassword(inputCustomer.getPassword());
        myUser.setEmail(inputCustomer.getEmail());
        myUser.setPhoneNumber(inputCustomer.getPhoneNumber());
        myUser.setRole("CUSTOMER");
        authRepository.save(myUser);

        Customer customer = new Customer();
        customer.setName(inputCustomer.getName());
        customer.setAddress(customer.getAddress());
        customer.setUser(myUser);
        customerRepository.save(customer);
    }

    public void updateCustomer(Integer authId, Integer customerId, InputCustomer inputCustomer) {
        MyUser auth = authRepository.findMyUserById(authId);
        if (auth == null)
            throw new ApiException("Admin with ID: " + authId + " was not found");

        MyUser oldCustomer = authRepository.findMyUserById(customerId);
        if (oldCustomer == null)
            throw new ApiException("Customer with ID: " + customerId + " was not found");

        if (authId.equals(customerId) || auth.getRole().equals("ADMIN")) {
            oldCustomer.setUsername(inputCustomer.getUsername());
            oldCustomer.setPassword(inputCustomer.getPassword());
            oldCustomer.setEmail(inputCustomer.getEmail());
            oldCustomer.setPhoneNumber(inputCustomer.getPhoneNumber());
            oldCustomer.getCustomer().setName(inputCustomer.getName());
            oldCustomer.getCustomer().setAddress(inputCustomer.getAddress());
            authRepository.save(oldCustomer);
        } else throw new ApiException("You don't have access to update this customer");
    }

    public void deleteCustomer(Integer authId, Integer customerId) {
        MyUser auth = authRepository.findMyUserById(authId);
        if (auth == null)
            throw new ApiException("Customer with ID: " + authId + " was not found");

        MyUser oldCustomer = authRepository.findMyUserById(customerId);
        if (oldCustomer == null)
            throw new ApiException("Customer with ID: " + customerId + " was not found");

        if (authId.equals(customerId) || auth.getRole().equals("ADMIN"))
            authRepository.delete(oldCustomer);

        else throw new ApiException("You don't have the permission to delete a customer");
    }


    // Customer Cancel request
    public void customerDecision(Integer customerId, Integer customerRequestId, String decision) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw  new ApiException("Customer not found");
        }
        DriverRequest driverRequest = driverRequestRepository.findDriverRequestById(customerRequestId);
        if (driverRequest == null) {
            throw  new ApiException("The request not found");
        }
        Driver driver = driverRepository.findDriverById(driverRequest.getDriver().getId());
        DriverRequest checkRequestWithCustomer = driverRequestRepository.findDriverRequestByIdAndCustomerAndDriverId(customerRequestId,customerId, driver.getId());
        if (!checkRequestWithCustomer.equals(driverRequest)) {
            throw  new ApiException("The request doesn't match");
        }
        if (checkRequestWithCustomer.getDriverRequestStatus().equals("Completed")){
            throw new ApiException("The Request has been completed");
        }

        if (decision.equalsIgnoreCase("cancel")) {
            driverRequest.setDriverRequestStatus("CANACELLED");
        }
        driverRequestRepository.save(driverRequest);
    }
}
