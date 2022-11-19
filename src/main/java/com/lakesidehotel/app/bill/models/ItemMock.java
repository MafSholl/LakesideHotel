package com.lakesidehotel.app.bill.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemMock {
    @Id
    private Long itemId;
    private String itemName;
    private int ItemQuantity;
    private BigDecimal itemPrice;
    private BigDecimal totalAmount;
    private Long customerId;
}
