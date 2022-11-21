package com.lakesidehotel.app.room.dto;

import com.lakesidehotel.app.users.model.Gender;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GuestInfoResponse {
    private String guestFirstName;
    private String guestLastName;
    private String guestPhoneNumber;
    private String guestEmail;
    private String guestAddress;
    private Gender gender;
}
