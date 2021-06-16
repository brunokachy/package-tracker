package com.tracker.entrypoint.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    public ApiResponse(String message) {
        this.message = message;
        this.data = null;
    }

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    private String message;
    private T data;
}

