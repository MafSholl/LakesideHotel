package com.lakesidehotel.app.bill.controller;

import com.lakesidehotel.app.bill.controller.responses.ApiResponse;
import com.lakesidehotel.app.bill.dto.BillDto;
import com.lakesidehotel.app.bill.dto.GenerateBillDto;
import com.lakesidehotel.app.bill.services.BillService;
import com.lakesidehotel.app.exceptions.exceptionClasses.LakesideHotelException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("generate-bill/{id}")
    public ResponseEntity<?> generateBill(@NonNull @PathVariable Long id) throws LakesideHotelException {
        GenerateBillDto guestId = new GenerateBillDto(id);
        BillDto bill = billService.generateBill(guestId);
        ApiResponse response = ApiResponse.builder()
                .status("success")
                .message("Bill created successfully")
                .data(bill)
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<> (response, HttpStatus.OK);
    }
}
