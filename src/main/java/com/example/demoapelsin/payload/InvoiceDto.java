package com.example.demoapelsin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceDto {
private Integer id;
private Integer customerId;
private java.sql.Date date;
private Double amount;
private java.sql.Date issued;
private Date due;


}
