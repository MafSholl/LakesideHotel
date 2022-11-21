package com.lakesidehotel.app.room.dto;

import com.lakesidehotel.app.room.model.RoomType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddRoomRequest {
    private String adminEmail;
    private String password;
    private int roomNumber;
    private String location;
    private RoomType roomType;
}
