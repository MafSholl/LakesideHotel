package com.lakesidehotel.app.room.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AvailableRoomRequest {
    private String email;
    private String password;
}
