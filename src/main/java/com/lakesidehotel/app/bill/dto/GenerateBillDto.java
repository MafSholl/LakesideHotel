package com.lakesidehotel.app.bill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GenerateBillDto {

    private Long guestId;
}
