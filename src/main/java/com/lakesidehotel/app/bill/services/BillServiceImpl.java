package com.lakesidehotel.app.bill.services;

import com.lakesidehotel.app.bill.dto.BillDto;
import com.lakesidehotel.app.bill.dto.GenerateBillDto;
import com.lakesidehotel.app.exceptions.LakesideHotelException;
import com.lakesidehotel.app.bill.models.Bill;
import com.lakesidehotel.app.bill.models.ItemMock;
import com.lakesidehotel.app.bill.repository.BillRepository;
import com.lakesidehotel.app.bill.services.mockServices.ItemMockService;
import com.lakesidehotel.app.guest.models.Guest;
import com.lakesidehotel.app.guest.repository.GuestRepository;
import com.lakesidehotel.app.room.exception.LakeSideHotelException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ItemMockService itemMockService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GuestRepository guestRepository;

    @Override
    public BillDto generateBill(GenerateBillDto generateBillDto) throws LakesideHotelException {
        if (generateBillDto == null) throw new LakesideHotelException("Request does not contain guest id");
        List<ItemMock> itemList = itemMockService.findItemsByCustomerId(generateBillDto.getGuestId());
        Optional<Guest> optionalGuest = guestRepository.findById(generateBillDto.getGuestId());
        if(optionalGuest.isEmpty()) throw new LakeSideHotelException("Guest does not exist");
        Guest guest = optionalGuest.get();
        Bill newBill = Bill.builder()
                .customerFirstName(guest.getFirstName())
                .customerLastName(guest.getLastName())
                .customerId(guest.getId())
                .itemList(itemList)
                .build();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (ItemMock item : itemList) {
             totalAmount = totalAmount.add(item.getTotalAmount());
        }
        newBill.setTotalAmount(totalAmount);
        billRepository.save(newBill);
        return modelMapper.map(newBill, BillDto.class);
    }
}
