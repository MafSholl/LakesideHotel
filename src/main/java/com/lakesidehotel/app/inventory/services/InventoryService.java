package com.lakesidehotel.app.inventory.services;


public interface InventoryService {
    int checkRoomInventoryStatus();

    int checkFoodInventoryStatus();

    int checkDrinkInventoryStatus();
}
