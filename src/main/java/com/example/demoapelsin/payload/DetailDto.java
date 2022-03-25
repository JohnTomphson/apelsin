package com.example.demoapelsin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailDto {
private Integer id;
private Integer orderId;
private Integer productId;
private int quantity;
}
