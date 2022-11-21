package com.lakesidehotel.app.receptionist.service;

import com.lakesidehotel.app.receptionist.dto.ReceptionistDto;

public interface ReceptionistService {
    ReceptionistDto createNewReceptionist(ReceptionistDto createReceptionistRequest);

//    CheckRoomResponse checkRoomAvailability(CheckRoomRequest checkRoomReques);
}
