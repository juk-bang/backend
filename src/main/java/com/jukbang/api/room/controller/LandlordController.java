package com.jukbang.api.room.controller;

import com.jukbang.api.room.dto.LandlordDto;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.request.UpdateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 집 주인 컨트롤러
 *
 * @author always0ne
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/landlord/rooms")
public class LandlordController {

    private final RoomService roomService;

    /**
     * 집주인 방 list GET
     *
     * @return (List) SellerRoomList
     */
    @GetMapping
    public PagedModel<EntityModel<LandlordDto>> getSellerRoomList(
            Pageable pageable,
            PagedResourcesAssembler<LandlordDto> assembler
    ) {
        String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        return assembler.toModel(roomService.getSellerRooms(requestUserId, pageable));
    }

    /**
     * 집주인 방 추가하기
     *
     * @param createRoomRequest 방 생성 데이터
     */
    @PostMapping
    public void registerRoom(
            @RequestBody CreateRoomRequest createRoomRequest
    ) {
        String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        roomService.registerRoom(requestUserId, createRoomRequest);
    }

    /**
     * 집주인 방 수정 UPDATE
     *
     * @param roomId            방 ID
     * @param updateRoomRequest 방 수정 데이터
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
     * 집주인 방 삭제 DELETE
     *
     * @param roomId 방 ID
     */
    @DeleteMapping("/{roomId}")
    public void deleteRoom(
            @PathVariable("roomId") Long roomId
    ) {
        String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        roomService.deleteRoom(requestUserId, roomId);
    }
}
