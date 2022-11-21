package com.lakesidehotel.app.foodItem.repositories;

import com.lakesidehotel.app.foodItem.models.FoodItem;
import com.lakesidehotel.app.foodItem.models.enums.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<FoodItem, Long> {

    Optional<FoodItem> findByFoodName(String foodName);

    boolean existsById(Food foodName);

    Optional<FoodItem> findByDrinkName(String drinkName);
}
