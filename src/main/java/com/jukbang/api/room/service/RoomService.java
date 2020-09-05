package com.jukbang.api.room.service;

import com.jukbang.api.room.dto.LandlordDto;
import com.jukbang.api.room.dto.RoomsDto;
import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.exception.NotYourRoomException;
import com.jukbang.api.room.exception.RoomNotFoundException;
import com.jukbang.api.room.repository.RoomRepository;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.request.UpdateRoomRequest;
import com.jukbang.api.room.response.GetRoomDetailResponse;
import com.jukbang.api.user.exception.UserNotFoundException;
import com.jukbang.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Transactional
    public Page<RoomsDto> getRooms(long univId/*, long layout, long monthlyLease, long adminExpenses, long deposit, long scale, long grade, long distance, long floor*/, Pageable pageable) {
        // 필터 미구현
        System.out.println("run");
        return this.roomRepository.findAllByUnivId(univId,pageable);
    }

    @Transactional
    public Page<LandlordDto> getSellerRooms(String sellerId, Pageable pageable) {
        return roomRepository.findAllBySeller_UserId(sellerId,pageable);
    }

    @Transactional
    public GetRoomDetailResponse getRoomDetail(long roomId) {
        return new GetRoomDetailResponse(roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new));
    }

    @Transactional
    public Long registerRoom(String sellerId, CreateRoomRequest createRoomRequest) {
        return roomRepository.save(
                new Room(createRoomRequest.getUnivId(),
                        createRoomRequest.getPrice(),
                        createRoomRequest.getRoomInfo(),
                        createRoomRequest.getOption(),
                        createRoomRequest.getLocation(),
                        createRoomRequest.getDescription(),
                        userRepository.findByUserId(sellerId).orElseThrow(()->new UserNotFoundException(sellerId))
                )).getRoomId();
    }


    @Transactional
    public void updateRoom(String sellerId, long roomId, UpdateRoomRequest updateRoomRequest) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new UserNotFoundException(sellerId));
        if (!room.getSeller().getUserId().equals(sellerId))
            throw new NotYourRoomException();
        room.updateRoom(
                updateRoomRequest.getUnivId(),
                updateRoomRequest.getPrice(),
                updateRoomRequest.getRoomInfo(),
                updateRoomRequest.getOption(),
                updateRoomRequest.getLocation(),
                updateRoomRequest.getDescription()
        );
    }

    @Transactional
    public void deleteRoom(String sellerId, Long roomId) {
        if (roomRepository.existsByRoomIdAndSeller_UserId(roomId, sellerId))
            roomRepository.deleteById(roomId);
        else
            throw new NotYourRoomException();
    }

    @Transactional
    public List<Room> wantpermit(long Univid) {
/*
        return roomRepository.findAllByUnividAndPermission(Univid, 0);
*/
        return null;
    }

    @Transactional
    public void permit(long Univid, long id) {
/*        Optional<Room> room = roomRepository.findById(id);
        Room target = room.get();
        target.setPermission(1);
        roomRepository.save(target);*/
    }

    @Transactional
    public void dontpermit(long Univid, long id) {
/*        Optional<Room> room = roomRepository.findById(id);
        Room target = room.get();
        target.setPermission(2);
        roomRepository.save(target);*/
    }


}
