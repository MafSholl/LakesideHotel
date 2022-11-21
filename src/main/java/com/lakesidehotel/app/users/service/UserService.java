package com.lakesidehotel.app.users.service;

import com.lakesidehotel.app.room.exception.LakeSideHotelException;
import com.lakesidehotel.app.users.model.User;

public interface UserService {
    String registerUser(RegisterRequest registerRequest) throws LakeSideHotelException;
    String login(LoginRequest loginRequest) throws LakeSideHotelException;
    User getUserByUsername(String username);

}
