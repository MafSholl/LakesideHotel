package com.lakesidehotel.app.bill.dto;

import com.lakesidehotel.app.bill.models.ItemMock;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BillDto {
    private int billNo;
    private String customerFirstName;
    private String customerLastName;
    private List<ItemMock> itemList;
    private BigDecimal totalAmount;

}
