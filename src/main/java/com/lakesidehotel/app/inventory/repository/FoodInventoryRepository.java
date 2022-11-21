package com.lakesidehotel.app.inventory.repository;

import com.lakesidehotel.app.inventory.models.FoodInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodInventoryRepository extends JpaRepository<FoodInventory, Long> {
}
