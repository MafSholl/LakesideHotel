package com.lakesidehotel.app.room.service;

import com.lakesidehotel.app.admin.models.Admin;
import com.lakesidehotel.app.admin.repository.AdminRepository;
import com.lakesidehotel.app.guest.models.Guest;
import com.lakesidehotel.app.guest.repository.GuestRepository;
import com.lakesidehotel.app.room.dto.AddRoomRequest;
import com.lakesidehotel.app.room.dto.BookRoomDto;
import com.lakesidehotel.app.room.dto.BookRoomRequest;
import com.lakesidehotel.app.room.dto.GuestInfoResponse;
import com.lakesidehotel.app.room.exception.LakeSideHotelException;
import com.lakesidehotel.app.room.model.BookedRoom;
import com.lakesidehotel.app.room.model.Room;
import com.lakesidehotel.app.room.model.RoomStatus;
import com.lakesidehotel.app.room.model.RoomType;
import com.lakesidehotel.app.room.repository.BookedRoomRepository;
import com.lakesidehotel.app.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.lakesidehotel.app.utils.ValidateEmail.isValidEmail;


@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final BookedRoomRepository bookedRoomRepository;
    private final GuestRepository guestRepository;
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;


    @Override
    public String addRoom(AddRoomRequest addRoomRequest) throws LakeSideHotelException {
        Optional<Admin> admin = adminRepository.findByEmail(addRoomRequest.getAdminEmail());
        if (admin.isPresent()){
            if (admin.get().getPassword().equals(addRoomRequest.getPassword())){
                Room newRoom = modelMapper.map(addRoomRequest, Room.class);
                newRoom.setRoomStatus(RoomStatus.AVAILABLE);
                roomRepository.save(newRoom);
                return "Room " + newRoom.getRoomNumber() + " has been added successfully";
            }
            throw new LakeSideHotelException("Invalid login details");
        }
        throw new LakeSideHotelException("Invalid login details");
    }

    @Override
    public List<Room> getAvailableRooms() throws LakeSideHotelException {
        List<Room> rooms = roomRepository.findAll();
        List<Room> availableRoom = new ArrayList<>();
        for(Room room: rooms){
            if (room.getRoomStatus().equals(RoomStatus.AVAILABLE)){
                availableRoom.add(room);
            }
        }
        if (availableRoom.isEmpty()){
            throw new LakeSideHotelException("Sorry, no available room at the moment");
        }
        return availableRoom;
    }

    @Override
    public GuestInfoResponse getGuestInfo(int roomNumber) {
        BookedRoom room = bookedRoomRepository.findByRoomNumber(roomNumber);
        Guest guest = room.getGuest();
        return getGuestInfoResponse(guest);
    }

    private static GuestInfoResponse getGuestInfoResponse(Guest guest) {
        return GuestInfoResponse.builder()
                .guestFirstName(guest.getFirstName())
                .guestLastName(guest.getLastName())
                .guestAddress(guest.getAddress())
                .guestEmail(guest.getEmail())
                .guestPhoneNumber(guest.getPhoneNumber())
                .build();
    }

    @Override
    public boolean isRoomAvailable(int roomNumber) {
        Room room = roomRepository.findByRoomNumber(roomNumber);
        return room.getRoomStatus().equals(RoomStatus.AVAILABLE);
    }

    @Override
    public BookRoomDto bookARoom(BookRoomRequest roomRequest) throws LakeSideHotelException {
        if (isValidEmail(roomRequest.getGuestEmail())){
            Optional<Guest> guest = guestRepository.findByEmail(roomRequest.getGuestEmail());
            if (guest.isPresent()){
                return bookARoomForExistingGuest(guest.get(), roomRequest.getRoomType());
            }
            return bookRoomForANewGuest(roomRequest);
        }
        throw new LakeSideHotelException("This email is not valid");
    }

    @Override
    public String checkOut(String email) throws LakeSideHotelException {
        Optional<Guest> guest = guestRepository.findByEmail(email);
        if (guest.isPresent()){
            BookedRoom room = bookedRoomRepository.findRoomByGuest(guest.get());
            if (room.getRoomStatus().equals(RoomStatus.OCCUPIED)){
                room.setRoomStatus(RoomStatus.AVAILABLE);
                room.setHasBookedARoom(false);
                room.setCheckOutTime(LocalDateTime.now());
                bookedRoomRepository.save(room);
                Room leavingRoom = roomRepository.findByRoomNumber(room.getRoomNumber());
                leavingRoom.setRoomStatus(RoomStatus.AVAILABLE);
                roomRepository.save(leavingRoom);
                return "You have successfully checked out. Hope you enjoyed your stay with us and we hope to see you again";
            }
            throw new LakeSideHotelException("You have not booked a room, So you can't check out");
        }
        throw new LakeSideHotelException("You have not booked a room, So you can't check out");
    }

    @Override
    public String checkIn(String email) throws LakeSideHotelException {
        Optional<Guest> guest = guestRepository.findByEmail(email);
        if (guest.isPresent()){
            BookedRoom bookedRoom = bookedRoomRepository.findRoomByGuest(guest.get());
            System.out.println(bookedRoom.toString());
            if (bookedRoom.isHasBookedARoom()){
                bookedRoom.setCheckInTime(LocalDateTime.now());
                bookedRoomRepository.save(bookedRoom);
                return "You have successfully checked In. Hope you will enjoy your stay in our hotel";
            }
            throw new LakeSideHotelException("You have not booked a room");
        }
        throw new LakeSideHotelException("You have not booked a room, So you can't check out");
    }

    private BookRoomDto bookARoomForExistingGuest(Guest guest, RoomType roomType) throws LakeSideHotelException {
        Room room = getARoom(roomType);
        BookedRoom bookedRoom = BookedRoom.builder()
                .hasBookedARoom(true)
                .roomStatus(room.getRoomStatus())
                .bookingTime(LocalDateTime.now())
                .guest(guest)
                .roomType(room.getRoomType())
                .roomNumber(room.getRoomNumber())
                .location(room.getLocation())
                .build();
        bookedRoomRepository.save(bookedRoom);
        return roomDto(bookedRoom);
    }

    private BookRoomDto bookRoomForANewGuest(BookRoomRequest roomRequest) throws LakeSideHotelException {
        Guest newGuest = newGuest(roomRequest);
        return bookARoomForExistingGuest(newGuest, roomRequest.getRoomType());
    }

    private Room getARoom(RoomType roomType) throws LakeSideHotelException {
        List<Room> availableRoom = getAvailableRooms();
        List<Room> selectedRoom = new ArrayList<>();
        for (Room room: availableRoom) {
            if (room.getRoomType().equals(roomType)){
                selectedRoom.add(room);
            }
        }
        SecureRandom random = new SecureRandom();
        Room room = selectedRoom.get(random.nextInt(selectedRoom.size()));
        room.setRoomStatus(RoomStatus.OCCUPIED);
        return room;
    }
    private Guest newGuest(BookRoomRequest roomRequest){
        Guest newGuest = modelMapper.map(roomRequest, Guest.class);
        guestRepository.save(newGuest);
        return newGuest;
    }
    private BookRoomDto roomDto(BookedRoom room){
        return BookRoomDto.builder()
                .roomNumber(room.getRoomNumber())
                .location(room.getLocation())
                .isSuccessful(true).build();
    }
}


