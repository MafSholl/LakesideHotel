package com.lakesidehotel.app.bill.services.mockServices;

import com.lakesidehotel.app.bill.models.ItemMock;

import java.util.List;

public interface ItemMockService {

    List<ItemMock> findItemsByCustomerId(Long CustomerId);
}
