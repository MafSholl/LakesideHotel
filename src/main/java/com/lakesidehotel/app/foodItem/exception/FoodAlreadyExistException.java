package com.lakesidehotel.app.foodItem.exception;

public class FoodAlreadyExistException extends FoodException{
    public FoodAlreadyExistException(String message){
        super(message);
    }
}
