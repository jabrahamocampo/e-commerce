package com.ecommerce.authservice.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {

    private LocalDateTime timestamp;

    private String message;

    private Integer status;

    private T data;

}
