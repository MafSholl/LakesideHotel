package com.lakesidehotel.app.room.repository;

import com.lakesidehotel.app.guest.models.Guest;
import com.lakesidehotel.app.room.model.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {
    BookedRoom findByRoomNumber(int roomNumber);
    BookedRoom findRoomByGuest(Guest guest);

}
