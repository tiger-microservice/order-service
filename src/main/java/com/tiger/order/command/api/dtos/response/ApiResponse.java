package com.tiger.order.command.api.dtos.response;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {

    private int status;
    private T data;
    private String message;

    public static <T> ApiResponse responseOK(T data) {
        return ApiResponse.builder().status(HttpStatus.OK.value()).data(data).build();
    }

    public static <T> ApiResponse responseOK() {
        return ApiResponse.builder().status(HttpStatus.OK.value()).build();
    }

    public static <T> ApiResponse responseError(int code, String message) {
        return ApiResponse.builder().status(code).message(message).build();
    }
}
