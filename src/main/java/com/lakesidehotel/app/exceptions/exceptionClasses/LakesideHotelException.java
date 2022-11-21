package com.lakesidehotel.app.exceptions.exceptionClasses;

public class LakesideHotelException extends RuntimeException {
    public LakesideHotelException(String message) {
        super(message);
    }

    public LakesideHotelException(String message, int statusCode) {
        super(message);
    }
}
