package com.jukbang.api.room.controller;

import com.jukbang.api.room.dto.RoomDetailWrapper;
import com.jukbang.api.room.dto.RoomlistWrapper;
import com.jukbang.api.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    /**
     * 방 리스트 GET
     *
     * @param univId
     * @return (RoomlistWrapper) roomList
     */
    @CrossOrigin(origins = "*")
    @GetMapping("roomlist/{univId}")
    public RoomlistWrapper getRoomList(
            @PathVariable("univId") long univId
    ) {
        RoomlistWrapper roomList = roomService.getRoomlist(univId);
        return roomList;
    }

    /**
     * 방 별 세부사항 GET
     *
     * @param univId
     * @param roomId
     * @return (RoomDetailWrapper) roomDetail
     */
    @CrossOrigin(origins = "*")
    @GetMapping("roomdetail/{univId}/{roomId}")
    public RoomDetailWrapper getRoomDetail(
            @PathVariable("univId") long univId,
            @PathVariable("roomId") long roomId
    ) {
        RoomDetailWrapper roomDetail = roomService.getRoomDetail(roomId);
        return roomDetail;
    }
}

