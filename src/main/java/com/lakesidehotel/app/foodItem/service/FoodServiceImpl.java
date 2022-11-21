package com.lakesidehotel.app.foodItem.service;

import com.example.lakesidehotel.app.foodItem.Mapper;
import com.example.lakesidehotel.app.foodItem.dto.request.AddFoodRequest;
import com.example.lakesidehotel.app.foodItem.dto.response.AddFoodResponse;
import com.example.lakesidehotel.app.foodItem.exception.FoodAlreadyExistException;
import com.example.lakesidehotel.app.foodItem.models.FoodItem;
import com.example.lakesidehotel.app.foodItem.repositories.FoodRepository;
import com.example.lakesidehotel.app.room.exception.LakeSideHotelException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FoodServiceImpl implements FoodService{

    private final FoodRepository foodRepository;

    @Override
    public AddFoodResponse addFood(AddFoodRequest request)throws FoodAlreadyExistException {
        if (foodRepository.existsById(request.getFoodName())) throw new FoodAlreadyExistException("Food Already exist");
        FoodItem food = FoodItem.builder()
                .foodName(request.getFoodName())
                .drinkName(request.getDrinkName())
                .nativeSoup(request.getNativeSoup())
                .nativeMeal(request.getNativeMeal())
                .amount(request.getAmount())
                .build();
        String foodId = String.valueOf(UUID.randomUUID().getMostSignificantBits());
        foodId = "FID" + foodId.substring(1,4);
        food.setFoodId(Long.valueOf(foodId));

        if (foodRepository.existsById(food.getFoodId())){
            String newFoodId;
            newFoodId = "FID" + foodId.substring(1,4);
            food.setFoodId(Long.valueOf(newFoodId));
        }
        FoodItem savedFood = foodRepository.save(food);
        AddFoodResponse response = new AddFoodResponse();
        Mapper.map(savedFood, response);
        return response;
    }
    @Override
    public boolean isFoodAvailable(String foodName) throws LakeSideHotelException {
        Optional<FoodItem> food = foodRepository.findByFoodName(foodName);
        if (food.isEmpty()) throw new LakeSideHotelException("Food not available");
        return true;
    }

    @Override
    public  boolean isDrinksAvailable(String drinkName) throws LakeSideHotelException{
        Optional<FoodItem> drink = foodRepository.findByDrinkName(drinkName);
        if(drink.isEmpty()) throw new LakeSideHotelException("Drink not available");
        return true;
    }




}
//write a method getAllAvailableFood and drinks
//get total bill
