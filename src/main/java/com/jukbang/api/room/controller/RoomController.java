package com.jukbang.api.room.controller;

import com.jukbang.api.room.dto.RoomsDto;
import com.jukbang.api.room.response.GetRoomDetailResponse;
import com.jukbang.api.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

/**
 * 방 컨트롤러
 *
 * @author always0ne
 * @version 1.0
 */
@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    /**
     * 방 리스트 가져오기
     *
     * @param univId 대학 ID
     * @return (RoomlistWrapper) roomList
     */
    @GetMapping
    public PagedModel<EntityModel<RoomsDto>> getRoomList(
            Pageable pageable,
            PagedResourcesAssembler<RoomsDto> assembler,
            @RequestParam(value = "univId") Long univId,
            @RequestParam(value = "layout", required = false) Integer layout,
            @RequestParam(value = "floor", required = false) Double floor,
            @RequestParam(value = "scale", required = false) Double scale,
            @RequestParam(value = "monthlyLease", required = false) Double monthlyLease,
            @RequestParam(value = "adminExpenses", required = false) Double adminExpenses,
            @RequestParam(value = "deposit", required = false) Double deposit,
            @RequestParam(value = "grade", required = false) Double grade,
            @RequestParam(value = "distance", required = false) Double distance
    ) {
        return assembler.toModel(roomService.getRooms(univId, layout, floor, scale, monthlyLease, adminExpenses, deposit, grade, distance, pageable));
    }

    /**
     * 방 별 세부사항 GET
     *
     * @param roomId 방 ID
     * @return (RoomDetailWrapper) roomDetail
     */
    @GetMapping("/{roomId}")
    public GetRoomDetailResponse getRoomDetail(
            @PathVariable("roomId") long roomId
    ) {
        return roomService.getRoomDetail(roomId);
    }
}

