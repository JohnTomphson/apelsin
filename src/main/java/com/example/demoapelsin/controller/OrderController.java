package com.example.demoapelsin.controller;

import com.example.demoapelsin.entity.Categorys;
import com.example.demoapelsin.entity.Orders;
import com.example.demoapelsin.payload.ApiResponse;
import com.example.demoapelsin.payload.InvoiceDto;
import com.example.demoapelsin.payload.OrderDto;
import com.example.demoapelsin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    final OrderService orderService;

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = orderService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse apiResponse = orderService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody InvoiceDto invoiceDto) {
        ApiResponse apiResponse = orderService.add(invoiceDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id,@RequestBody OrderDto orderDto,@RequestBody InvoiceDto invoiceDto) {
        ApiResponse apiResponse = orderService.update(id, orderDto, invoiceDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 404).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = orderService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
    @GetMapping("/expired_invoices")
    public  HttpEntity<?> getExpired(){
        ApiResponse apiResponse = orderService.expired();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    
}
