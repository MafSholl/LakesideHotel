package com.lakesidehotel.app.foodItem;

import com.example.lakesidehotel.app.foodItem.dto.request.AddFoodRequest;
import com.example.lakesidehotel.app.foodItem.dto.response.AddFoodResponse;
import com.example.lakesidehotel.app.foodItem.models.FoodItem;
import lombok.Builder;

@Builder
public class Mapper {

    public static void map(FoodItem savedFood, AddFoodResponse response) {
        response.setMessage("Food is Added " + savedFood.getFoodName() + ". your food id is " + savedFood.getFoodId());
    }


    public static FoodItem map(AddFoodRequest request) {
        FoodItem food = FoodItem.builder()
                .nativeMeal(request.getNativeMeal())
                .nativeSoup(request.getNativeSoup())
                .foodName(request.getFoodName())
                .foodId(request.getFoodId())
                .amount(request.getAmount())
                .drinkName(request.getDrinkName())
                .build();
        return food;

    }
    public static FoodItem map2(AddFoodRequest request){
        FoodItem food = new FoodItem();
        food.setFoodId(request.getFoodId());
        food.setFoodName(request.getFoodName());
        food.setDrinkName(request.getDrinkName());
        food.setNativeMeal(request.getNativeMeal());
        food.setNativeSoup(request.getNativeSoup());
        food.setAmount(request.getAmount());
        return food;
    }
}
