package com.lakesidehotel.app.guest.dto;

import lombok.Data;

@Data
public class GuestDto {
    private int id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String address;
    private int roomNo;
}
