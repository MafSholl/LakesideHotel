package com.lakesidehotel.app.receptionist.service;

import com.lakesidehotel.app.exceptions.LakesideHotelException;
import com.lakesidehotel.app.feedback.dto.FeedbackDto;
import com.lakesidehotel.app.feedback.service.FeedbackService;
import com.lakesidehotel.app.receptionist.dto.CheckRoomAvailabilityDto;
import com.lakesidehotel.app.receptionist.dto.ReceptionistDto;
import com.lakesidehotel.app.receptionist.models.Receptionist;
import com.lakesidehotel.app.receptionist.repository.ReceptionistRepository;
import com.lakesidehotel.app.room.dto.BookRoomDto;
import com.lakesidehotel.app.room.dto.BookRoomRequest;
import com.lakesidehotel.app.room.exception.LakeSideHotelException;
import com.lakesidehotel.app.room.models.RoomType;
import com.lakesidehotel.app.room.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceptionistServiceImpl implements ReceptionistService{

    @Autowired
    private ReceptionistRepository receptionistRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoomService roomService;
    @Autowired
    private FeedbackService feedBackService;

    @Override
    public ReceptionistDto createNewReceptionist(ReceptionistDto createReceptionistRequest) {

        Receptionist newReceptionist = modelMapper.map(createReceptionistRequest, Receptionist.class);
        Receptionist savedReceptionist = receptionistRepository.save(newReceptionist);
        return modelMapper.map(savedReceptionist, ReceptionistDto.class);
    }

    @Override
    public CheckRoomAvailabilityDto checkRoomAvailability(CheckRoomAvailabilityDto checkRoomAvailabilityRequest) {
        boolean roomServiceResponse = roomService.isRoomAvailable(checkRoomAvailabilityRequest.getRoomNo());
        CheckRoomAvailabilityDto roomAvailabilityDto = CheckRoomAvailabilityDto.builder()
                .roomNo(checkRoomAvailabilityRequest.getRoomNo())
                .available(roomServiceResponse)
                .build();
        return roomAvailabilityDto;
    }

    @Override
    public BookRoomDto bookRoom(BookRoomRequest bookRoomRequest) throws LakesideHotelException {
        BookRoomRequest roomRequest = BookRoomRequest.builder()
                .guestFirstName(bookRoomRequest.getGuestFirstName())
                .guestLastName(bookRoomRequest.getGuestLastName())
                .guestAddress(bookRoomRequest.getGuestAddress())
                .guestEmail(bookRoomRequest.getGuestEmail())
                .guestPhoneNumber(bookRoomRequest.getGuestPhoneNumber())
                .roomType(RoomType.DIAMOND)
                .build();
        return roomService.bookARoom(roomRequest);
    }

    @Override
    public FeedbackDto acceptCustomerFeedBack(FeedbackDto feedBackRequest) {
        FeedbackDto savedFeedBack = feedBackService.addFeedBack(feedBackRequest);
        FeedbackDto feedBackResponse = modelMapper.map(savedFeedBack, FeedbackDto.class);
        return feedBackResponse;
    }
}
