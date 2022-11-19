package com.lakesidehotel.app.receptionist.dto;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CheckRoomAvailabilityDto {
    @NonNull
    private int roomNo;
    private boolean available;
}
