package com.lakesidehotel.app.bill.services;

import com.lakesidehotel.app.bill.dto.BillDto;
import com.lakesidehotel.app.bill.dto.GenerateBillDto;
import com.lakesidehotel.app.exceptions.LakesideHotelException;

public interface BillService {


    BillDto generateBill(GenerateBillDto generateBillDto) throws LakesideHotelException;
}
