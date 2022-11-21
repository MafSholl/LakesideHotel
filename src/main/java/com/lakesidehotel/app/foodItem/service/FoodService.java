package com.lakesidehotel.app.foodItem.service;

import com.lakesidehotel.app.foodItem.dto.request.AddFoodRequest;
import com.lakesidehotel.app.foodItem.dto.response.AddFoodResponse;
import com.lakesidehotel.app.foodItem.exception.FoodAlreadyExistException;
import com.lakesidehotel.app.room.exception.LakeSideHotelException;

public interface FoodService {
    AddFoodResponse addFood(AddFoodRequest request) throws FoodAlreadyExistException;

    boolean isFoodAvailable(String FoodName) throws LakeSideHotelException;

    boolean isDrinksAvailable(String drinkName) throws LakeSideHotelException;
}
