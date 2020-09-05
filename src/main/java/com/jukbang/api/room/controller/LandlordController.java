package com.jukbang.api.room.controller;

import com.jukbang.api.room.dto.LandlordDto;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.request.UpdateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/landlord")
public class LandlordController {

    private final RoomService roomService;

    /**
     * 판매자 방 list GET
     *
     * @return (List) SellerRoomList
     */
    @GetMapping
    public Page<LandlordDto> getSellerRoomList(
            Pageable pageable,
            PagedResourcesAssembler<LandlordDto> assembler
    ) {
        String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        return roomService.getSellerRooms(requestUserId,pageable);
    }

    /**
     * 판매자 방 추가하기
     *
     * @param createRoomRequest
     */
    @PostMapping
    public void registerRoom(
            @RequestBody CreateRoomRequest createRoomRequest
    ) {
        String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        roomService.registerRoom(requestUserId, createRoomRequest);
    }

    /**
     * 판매자 방 수정 UPDATE
     *
     * @param roomId
     * @param updateRoomRequest
     */
    @PutMapping("/{roomId}")
    public void updateRoom(
            @PathVariable("roomId") Long roomId,
            @RequestBody UpdateRoomRequest updateRoomRequest
    ) {
        String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        roomService.updateRoom(requestUserId, roomId, updateRoomRequest);
    }

    /**
     * 판매자 방 삭제 DELETE
     *
     * @param roomId
     */
    @DeleteMapping("/{roomId}")
    public void deleteRoom(
            @PathVariable("roomId") Long roomId
    ) {
        String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        roomService.deleteRoom(requestUserId, roomId);
    }

}
