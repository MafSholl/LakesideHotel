package com.lakesidehotel.app.room.service;

import com.lakesidehotel.app.room.dto.AddRoomRequest;
import com.lakesidehotel.app.room.dto.BookRoomDto;
import com.lakesidehotel.app.room.dto.BookRoomRequest;
import com.lakesidehotel.app.room.dto.GuestInfoResponse;
import com.lakesidehotel.app.room.exception.LakeSideHotelException;
import com.lakesidehotel.app.room.model.Room;

import java.util.List;

public interface RoomService {
    String addRoom(AddRoomRequest addRoomRequest) throws LakeSideHotelException;
    List<Room> getAvailableRooms() throws LakeSideHotelException;
    GuestInfoResponse getGuestInfo(int roomNumber);
    boolean isRoomAvailable(int roomNumber);
    BookRoomDto bookARoom(BookRoomRequest roomRequest) throws LakeSideHotelException;
    String checkIn(String email) throws LakeSideHotelException;
    String checkOut(String email) throws LakeSideHotelException;

}
