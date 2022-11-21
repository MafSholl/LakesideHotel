package com.lakesidehotel.app.room.repository;

import com.lakesidehotel.app.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByRoomNumber(int roomNumber);

}
