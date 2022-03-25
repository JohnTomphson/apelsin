package com.example.demoapelsin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    private boolean success;
    private String message;
    private Object object;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
