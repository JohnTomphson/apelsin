package com.example.demoapelsin.repository;

import com.example.demoapelsin.entity.Customer;
import com.example.demoapelsin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByActiveTrue();

}
