package com.lakesidehotel.app.guest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDto {
    private int id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String address;
    private int roomNo;
}
