package com.lakesidehotel.app.foodItem.controller;

import com.lakesidehotel.app.foodItem.dto.request.AddFoodRequest;
import com.lakesidehotel.app.foodItem.dto.response.AddFoodResponse;
import com.lakesidehotel.app.foodItem.exception.FoodAlreadyExistException;
import com.lakesidehotel.app.foodItem.service.FoodService;
import com.lakesidehotel.app.room.exception.LakeSideHotelException;
//import com.lakesidehotel.app.foodItem.dto.request.AddFoodRequest;
//import com.lakesidehotel.app.foodItem.dto.response.AddFoodResponse;
//import com.lakesidehotel.app.foodItem.exception.FoodAlreadyExistException;
//import com.lakesidehotel.app.foodItem.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fooditem")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/addfood")
    private AddFoodResponse addFood(@RequestBody AddFoodRequest request) throws FoodAlreadyExistException {
        return foodService.addFood(request);
    }
    @GetMapping("/isFoodAvailable")
    public boolean isFoodAvailable(@PathVariable String foodName) throws LakeSideHotelException {
        return foodService.isFoodAvailable(foodName);
    }

    @GetMapping("/isDrinkAvailable")
    public boolean isDrinkAvailable(@PathVariable String drinkName) throws LakeSideHotelException {
        return foodService.isFoodAvailable(drinkName);
    }
}
