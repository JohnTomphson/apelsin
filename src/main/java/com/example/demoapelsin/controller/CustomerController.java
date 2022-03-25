package com.example.demoapelsin.controller;

import com.example.demoapelsin.entity.Categorys;
import com.example.demoapelsin.entity.Customer;
import com.example.demoapelsin.payload.ApiResponse;
import com.example.demoapelsin.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    final CustomerService customerService;

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = customerService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse apiResponse = customerService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody Customer customer) {
        ApiResponse apiResponse = customerService.add(customer);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody Customer customer) {
        ApiResponse apiResponse = customerService.update(id, customer);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 404).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = customerService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
