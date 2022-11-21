package com.lakesidehotel.app.users.userContoller;

import com.lakesidehotel.app.room.dto.AddRoomRequest;
import com.lakesidehotel.app.room.exception.LakeSideHotelException;
import com.lakesidehotel.app.room.service.RoomService;
import com.lakesidehotel.app.users.service.LoginRequest;
import com.lakesidehotel.app.users.service.RegisterRequest;
import com.lakesidehotel.app.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lakeSideHotel/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;

    @PostMapping("staff/register/")
    public String register(@RequestBody RegisterRequest registerRequest) throws LakeSideHotelException, LakeSideHotelException {
        return userService.registerUser(registerRequest);
    }
    @PostMapping("staff/login/")
    public String login(@RequestBody LoginRequest loginRequest) throws LakeSideHotelException {
        return userService.login(loginRequest);
    }
    @PostMapping("admin/addARoom/")
    public String addRoom(@RequestBody AddRoomRequest addRoomRequest) throws LakeSideHotelException {
        return roomService.addRoom(addRoomRequest);
    }
}
