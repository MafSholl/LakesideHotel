package com.lakesidehotel.app.exceptions.exceptionHandler;

import com.lakesidehotel.app.exceptions.exceptionClasses.LakesideHotelException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({LakesideHotelException.class})
    protected ResponseEntity<?> handleConflict(
            LakesideHotelException ex, WebRequest request) {
        ApiError apiError = new ApiError((HttpServletRequest) request);
        apiError.setStatus("Failure");
        apiError.setMessage(ex.getMessage());
        String bodyOfResponse = apiError.toString();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
