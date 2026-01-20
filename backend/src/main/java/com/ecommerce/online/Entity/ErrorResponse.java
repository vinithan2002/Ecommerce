package com.ecommerce.online.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private  int status;
    private String message;
    private String error;
    private LocalDateTime timeStamp;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
