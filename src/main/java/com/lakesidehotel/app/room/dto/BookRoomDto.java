package com.lakesidehotel.app.room.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookRoomDto {
    private int roomNumber;
    private String location;
    private boolean isSuccessful;
}
