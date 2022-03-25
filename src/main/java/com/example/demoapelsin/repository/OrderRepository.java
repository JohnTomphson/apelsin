package com.example.demoapelsin.repository;

import com.example.demoapelsin.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    List<Orders> findAllByActiveTrue();

    Optional<Orders> findByActiveTrueAndId(Integer id);
}
