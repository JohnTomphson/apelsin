package com.example.demoapelsin.repository;

import com.example.demoapelsin.entity.Customer;
import com.example.demoapelsin.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    List<Payment> findAllByActiveTrue();

}
