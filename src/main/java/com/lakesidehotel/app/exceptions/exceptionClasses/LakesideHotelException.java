package com.lakesidehotel.app.exceptions.exceptionClasses;

import lombok.Getter;

public class LakesideHotelException extends RuntimeException {
    @Getter
    private int statusCode;
    public LakesideHotelException(String message) {
        super(message);
    }

    public LakesideHotelException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
