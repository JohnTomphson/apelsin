package com.example.demoapelsin.repository;

import com.example.demoapelsin.entity.Categorys;
import com.example.demoapelsin.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findAllByActiveTrue();

    Optional<Customer> findById(Integer id);
    boolean existsByNameAndAddressAndPhoneNumberAndCountry(String name, String address,String phoneNumber,String country);

}
