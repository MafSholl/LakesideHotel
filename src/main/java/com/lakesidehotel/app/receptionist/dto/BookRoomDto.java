package com.lakesidehotel.app.receptionist.dto;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class BookRoomDto {
    @NonNull
    private int roomNo;
    private String location;
    private boolean success;
}
