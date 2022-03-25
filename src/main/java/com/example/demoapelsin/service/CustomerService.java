package com.example.demoapelsin.service;

import com.example.demoapelsin.entity.Customer;
import com.example.demoapelsin.payload.ApiResponse;
import com.example.demoapelsin.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final CustomerRepository customerRepository;


    public ApiResponse getAll() {
        List<Customer> customerList = customerRepository.findAllByActiveTrue();
        return customerList != null ? new ApiResponse(true, "Success", customerList) : new ApiResponse(false, "Failed");
    }


    public ApiResponse getById(Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.map(customer -> new ApiResponse(true, "Success", customer)).orElseGet(() -> new ApiResponse(false, "Not Found"));
    }


    public ApiResponse add(Customer customer) {
        boolean existsByNameAndAddressAndPhoneNumberAndCountry = customerRepository.existsByNameAndAddressAndPhoneNumberAndCountry(customer.getName(), customer.getAddress(), customer.getPhoneNumber(), customer.getCountry());
        if (existsByNameAndAddressAndPhoneNumberAndCountry) {
            return new ApiResponse(false, "This customer already exsists");
        }
        customerRepository.save(customer);
        return new ApiResponse(true, "Saved");
    }


    public ApiResponse update(Integer id, Customer updateCustomer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent()) {
            return new ApiResponse(false, "Not Found");
        }
        Customer customer = customerOptional.get();
        customer.setName(updateCustomer.getName());
        customer.setCountry(updateCustomer.getCountry());
        customer.setAddress(updateCustomer.getAddress());
        customer.setPhoneNumber(updateCustomer.getPhoneNumber());
        customerRepository.save(customer);
        return new ApiResponse(true, "updated", customer);
    }

    public ApiResponse delete(Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent()) {
            return new ApiResponse(false, "Not Found");
        } else {
            Customer customer = customerOptional.get();
            customer.setActive(false);
            customerRepository.save(customer);
            return new ApiResponse(true, "deleted");
        }
    }
//    customer category product order paymetdan

}
