package com.jukbang.api.room.controller;

import com.jukbang.api.room.dto.RoomDetailWrapper;
import com.jukbang.api.room.dto.RoomlistWrapper;
import com.jukbang.api.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class RoomController {

    private RoomService roomService;

    /**
     * 방 리스트 GET
     *
     * @param Univid
     * @return (RoomlistWrapper) roomList
     */
    @CrossOrigin(origins = "*")
    @GetMapping("roomlist/{Univid}")
    public RoomlistWrapper getRoomList(
            @PathVariable("univid") long univid
    ) {
        RoomlistWrapper roomList = roomService.getRoomlist(univid);
        return roomList;
    }

    /**
     * 방 별 세부사항 GET
     *
     * @param Univid
     * @param Roomid
     * @return (RoomDetailWrapper) roomDetail
     */
    @CrossOrigin(origins = "*")
    @GetMapping("roomdetail/{Univid}/{Roomid}")
    public RoomDetailWrapper getRoomDetail(
            @PathVariable("Univid") long Univid,
            @PathVariable("Roomid") long Roomid
    ) {
        RoomDetailWrapper roomDetail = roomService.getRoomDetail(Roomid);
        return roomDetail;
    }
}

