package com.lakesidehotel.app.receptionist.service;

import com.lakesidehotel.app.feedback.dto.FeedbackDto;
import com.lakesidehotel.app.receptionist.dto.CheckRoomAvailabilityDto;
import com.lakesidehotel.app.receptionist.dto.ReceptionistDto;
import com.lakesidehotel.app.room.dto.BookRoomDto;
import com.lakesidehotel.app.room.dto.BookRoomRequest;
import com.lakesidehotel.app.exceptions.LakesideHotelException;
import com.lakesidehotel.app.feedback.dto.FeedbackDto;

public interface ReceptionistService {
    ReceptionistDto createNewReceptionist(ReceptionistDto createReceptionistRequest);

    CheckRoomAvailabilityDto checkRoomAvailability(CheckRoomAvailabilityDto checkRoomAvailabilityRequest);

    BookRoomDto bookRoom(BookRoomRequest bookRoomRequest) throws LakesideHotelException;

    FeedbackDto acceptCustomerFeedBack(FeedbackDto feedBackRequest);

//    CheckRoomResponse checkRoomAvailability(CheckRoomRequest checkRoomReques);
}
