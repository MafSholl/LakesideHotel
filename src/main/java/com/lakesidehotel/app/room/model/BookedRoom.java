package com.lakesidehotel.app.room.model;

import com.lakesidehotel.app.users.model.Guest;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    private String location;
    @Enumerated(value = EnumType.STRING)
    private RoomStatus roomStatus;
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;
    @OneToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;
    private LocalDateTime checkOutTime;
    private LocalDateTime checkInTime;
    private LocalDateTime bookingTime;
    @Value(("${hasBookARoom}"))
    private boolean hasBookedARoom;
}
