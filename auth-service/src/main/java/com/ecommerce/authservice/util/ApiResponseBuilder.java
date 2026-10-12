package com.ecommerce.authservice.util;

import java.time.LocalDateTime;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.authservice.dto.response.ApiResponse;

public class ApiResponseBuilder {

    private ApiResponseBuilder() {

    }

    public static <T> ResponseEntity<ApiResponse<T>> success (String message, T data) {

        ApiResponse<T> response = ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.SC_OK)
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.ok(response);
    }     

    public static <T> ResponseEntity<ApiResponse<T>> created( String message, T data) {

        ApiResponse<T> response = ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.SC_CREATED)
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.SC_CREATED).body(response);
    }

}
