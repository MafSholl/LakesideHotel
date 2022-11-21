package com.lakesidehotel.app.foodItem.models;

import com.lakesidehotel.app.foodItem.models.enums.Drink;
import com.lakesidehotel.app.foodItem.models.enums.Food;
import com.lakesidehotel.app.foodItem.models.enums.NativeMeal;
import com.lakesidehotel.app.foodItem.models.enums.NativeSoup;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "FOOD_ITEM")
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @Column(name = "FOOD_ID")
    private Long foodId;

    @Column(name = "AMOUNT")
    private BigDecimal amount;
}
