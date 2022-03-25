package com.example.demoapelsin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String description;
    private Double price;
}
