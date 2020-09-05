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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    /**
     * 방 리스트 GET
     *
     * @param univId
     * @return (RoomlistWrapper) roomList
     */
    @GetMapping
    public PagedModel<EntityModel<RoomsDto>> getRoomList(
            Pageable pageable,
            PagedResourcesAssembler<RoomsDto> assembler,
            @RequestParam(value = "univId") Long univId/*,
            @RequestParam(value = "layout", required = false) Long layout,
            @RequestParam(value = "monthlyLease", required = false) Long monthlyLease,
            @RequestParam(value = "adminExpenses", required = false) Long adminExpenses,
            @RequestParam(value = "deposit", required = false) Long deposit,
            @RequestParam(value = "scale", required = false) Long scale,
            @RequestParam(value = "grade", required = false) Long grade,
            @RequestParam(value = "distance", required = false) Long distance,
            @RequestParam(value = "floor", required = false) Long floor*/
    ) {
        return assembler.toModel(roomService.getRooms(univId, pageable));
        //return assembler.toModel(roomService.getRooms(univId, layout, monthlyLease, adminExpenses, deposit, scale, grade, distance, floor, pageable));
    }

    /**
     * 방 별 세부사항 GET
     *
     * @param roomId
     * @return (RoomDetailWrapper) roomDetail
     */
    @GetMapping("/{roomId}")
    public GetRoomDetailResponse getRoomDetail(
            @PathVariable("roomId") long roomId
    ) {
        return roomService.getRoomDetail(roomId);
    }
}

