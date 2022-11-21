package com.lakesidehotel.app.inventory.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckFoodInventoryStatusDto {
    private Long id;
    private LocalDateTime inventoryTime;
    private String inventoryName;
}
