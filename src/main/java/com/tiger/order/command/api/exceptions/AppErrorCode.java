package com.tiger.order.command.api.exceptions;

import org.springframework.http.HttpStatusCode;

import com.tiger.cores.exceptions.BaseError;

import lombok.Getter;

@Getter
public enum AppErrorCode implements BaseError {
    ;

    AppErrorCode(String messageCode, HttpStatusCode statusCode) {
        this.messageCode = messageCode;
        this.statusCode = statusCode;
    }

    private String messageCode;
    private HttpStatusCode statusCode;

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public HttpStatusCode getHttpStatusCode() {
        return statusCode;
    }
}
