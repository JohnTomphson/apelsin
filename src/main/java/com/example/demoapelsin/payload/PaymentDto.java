package com.example.demoapelsin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {
    private Integer id;
    private Double amount;
    private Integer invoiceId;
}
