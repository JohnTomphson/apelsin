package com.example.demoapelsin.controller;

import com.example.demoapelsin.entity.Categorys;
import com.example.demoapelsin.payload.ApiResponse;
import com.example.demoapelsin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    final CategoryService categoryService;


    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = categoryService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody Categorys categorys) {
        ApiResponse apiResponse = categoryService.add(categorys);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody Categorys categorys) {
        ApiResponse apiResponse = categoryService.update(id, categorys);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 404).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
