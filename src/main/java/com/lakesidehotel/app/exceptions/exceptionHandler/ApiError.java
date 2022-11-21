package com.lakesidehotel.app.exceptions.exceptionHandler;

import lombok.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
    private LocalDateTime timeStamp;
    private String status;
    private String message;
    private String path;

    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }
    @Builder
    public ApiError(HttpServletRequest request) {
        this();
        this.path = request.getContextPath();
    }
}
