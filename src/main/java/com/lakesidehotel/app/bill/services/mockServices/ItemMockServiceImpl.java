package com.lakesidehotel.app.bill.services.mockServices;

import com.lakesidehotel.app.bill.models.ItemMock;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class ItemMockServiceImpl implements ItemMockService{

    private List<ItemMock> itemList;

    public ItemMockServiceImpl() {
        ItemMock itemMock = new ItemMock();
        itemMock.setItemId(11L);
        itemMock.setItemName("Baron");
        itemMock.setItemQuantity(2);
        itemMock.setItemPrice(new BigDecimal(30000));
        itemMock.setTotalAmount(BigDecimal.valueOf(60000));
        itemMock.setCustomerId(1L);

        itemList= new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            itemList.add(itemMock);
        }
    }
    @Override
    public List<ItemMock> findItemsByCustomerId(Long CustomerId) {
        return itemList;
    }
}
