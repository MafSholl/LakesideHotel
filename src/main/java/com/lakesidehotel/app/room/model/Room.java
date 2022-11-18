package com.lakesidehotel.app.room.model;

import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private int roomNumber;
    private String location;
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;
    @Enumerated(value = EnumType.STRING)
    private RoomStatus roomStatus;
}
