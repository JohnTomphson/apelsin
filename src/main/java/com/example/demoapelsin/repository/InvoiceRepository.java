package com.example.demoapelsin.repository;

import com.example.demoapelsin.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
}
