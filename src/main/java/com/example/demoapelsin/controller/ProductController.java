package com.example.demoapelsin.controller;

import com.example.demoapelsin.entity.Categorys;
import com.example.demoapelsin.payload.ApiResponse;
import com.example.demoapelsin.payload.ProductDto;
import com.example.demoapelsin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    final ProductService productService;

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = productService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse apiResponse = productService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.add(productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id,@RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.update(id,productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 404).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = productService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
