package com.lakesidehotel.app.inventory.services;

import com.lakesidehotel.app.inventory.repository.DrinkInventoryRepository;
import com.lakesidehotel.app.inventory.repository.FoodInventoryRepository;
import com.lakesidehotel.app.inventory.repository.RoomInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InventoryServiceImpl {
    @Autowired
    private DrinkInventoryRepository drinkInventoryRepository;

    @Autowired
    private FoodInventoryRepository foodInventoryRepository;

    @Autowired
    private RoomInventoryRepository roomInventoryRepository;

    @Autowired
    private RoomService roomService;

    @Override
    public int checkRoomInventoryStatus() throws LakeSideHotelException {
        int number = roomService.getAvailableRooms().size();
        RoomInventory roomInventory = RoomInventory.builder()
                .inventoryTime(LocalDateTime.now())
                .inventoryRoomCount(number).build();
        RoomInventory newRoomInventory = roomInventoryRepository.save(roomInventory);

        return newRoomInventory.getInventoryRoomCount();
    }

    @Override
    public CheckFoodInventoryStatusDto checkFoodInventoryStatus(CheckFoodInventoryStatusDto checkFoodInventoryRequest) {
        return null;
    }

    @Override
    public CheckDrinkInventoryStatusDto checkDrinkInventoryStatus(CheckDrinkInventoryStatusDto checkDrinkInventoryRequest) {
        return null;
    }
}
