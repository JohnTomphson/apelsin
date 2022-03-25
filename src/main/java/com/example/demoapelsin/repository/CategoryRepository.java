package com.example.demoapelsin.repository;

import com.example.demoapelsin.entity.Categorys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Categorys,Integer> {
    List<Categorys> findAllByActiveTrue();

    Optional<Categorys> findById(Integer id);
//    boolean existsByCategorys(Categorys categorys);
}
