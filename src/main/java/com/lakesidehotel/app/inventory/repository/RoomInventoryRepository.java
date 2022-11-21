package com.lakesidehotel.app.inventory.repository;

import com.lakesidehotel.app.inventory.models.RoomInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomInventoryRepository extends JpaRepository<RoomInventory, Long> {
}
