package com.example.demoapelsin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Timestamp timestamp;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    private Invoice invoice;

    @Column(nullable = false)
    private boolean active = true;
}
