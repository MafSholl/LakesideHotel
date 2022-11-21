package com.lakesidehotel.app.foodItem.dto.request;

import com.lakesidehotel.app.foodItem.models.enums.Drink;
import com.lakesidehotel.app.foodItem.models.enums.Food;
import com.lakesidehotel.app.foodItem.models.enums.NativeMeal;
import com.lakesidehotel.app.foodItem.models.enums.NativeSoup;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddFoodRequest {

    @Column(name = "FOOD_NAME")
    @Enumerated
    private Food foodName;

    @Column(name = "DRINK_NAME")
    @Enumerated
    private Drink drinkName;

    @Column(name = "NATIVE_MEAL")
    @Enumerated
    private NativeMeal nativeMeal;

    @Column(name = "NATIVE_SOUP")
    @Enumerated
    private NativeSoup nativeSoup;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    private Long FoodId;
}
