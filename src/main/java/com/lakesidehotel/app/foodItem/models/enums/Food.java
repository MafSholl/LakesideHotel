package com.lakesidehotel.app.foodItem.models.enums;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Food {
    RICE, BEANS, YAM;

    private String foodId;

    public String getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        String foodName = new String();
        return foodName;
    }
}
