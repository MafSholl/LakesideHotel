package com.lakesidehotel.app.inventory.repository;

import com.lakesidehotel.app.inventory.models.DrinkInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkInventoryRepository extends JpaRepository<DrinkInventory, Long> {
}
