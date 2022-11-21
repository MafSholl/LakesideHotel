package com.lakesidehotel.app.room.controller;


import com.lakesidehotel.app.room.dto.BookRoomDto;
import com.lakesidehotel.app.room.dto.BookRoomRequest;
import com.lakesidehotel.app.room.dto.GuestInfoResponse;
import com.lakesidehotel.app.room.exception.LakeSideHotelException;
import com.lakesidehotel.app.room.model.Room;
import com.lakesidehotel.app.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lakeSideHotel/")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("guest/bookARoom/")
    public BookRoomDto bookARoom(@RequestBody BookRoomRequest roomRequest) throws LakeSideHotelException {
        return roomService.bookARoom(roomRequest);
    }
    @GetMapping("getAllAvailableRooms/")
    public List<Room> getAllAvailableRooms() throws LakeSideHotelException {
        return roomService.getAvailableRooms();
    }
    @GetMapping("getGuestDetails/{roomNumber}/")
    public GuestInfoResponse getGuestDetails(@PathVariable int roomNumber){
        return roomService.getGuestInfo(roomNumber);
    }
    @GetMapping("isRoomAvailable/{roomNumber}/")
    public boolean isRoomAvailable(@PathVariable int roomNumber){
        return roomService.isRoomAvailable(roomNumber);
    }
    @GetMapping("checkIn/{email}")
    public String checkIn(@PathVariable String email) throws LakeSideHotelException {
        return roomService.checkIn(email);
    }
    @GetMapping("checkOut/{email}")
    public String checkOut(@PathVariable String email) throws LakeSideHotelException {
        return roomService.checkOut(email);
    }

}
