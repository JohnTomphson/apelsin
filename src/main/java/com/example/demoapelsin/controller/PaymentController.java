package com.example.demoapelsin.controller;

import com.example.demoapelsin.entity.Categorys;
import com.example.demoapelsin.entity.Payment;
import com.example.demoapelsin.payload.ApiResponse;
import com.example.demoapelsin.payload.PaymentDto;
import com.example.demoapelsin.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
    final PaymentService paymentService;

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = paymentService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse apiResponse = paymentService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody PaymentDto paymentDto) {
        ApiResponse apiResponse = paymentService.add(paymentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody PaymentDto paymentDto) {
        ApiResponse apiResponse = paymentService.update(id, paymentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 404).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = paymentService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
