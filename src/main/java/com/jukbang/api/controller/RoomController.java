package com.jukbang.api.controller;

import com.jukbang.api.dto.RoomDetailWrapper;
import com.jukbang.api.dto.RoomlistWrapper;
import com.jukbang.api.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RoomController {

    private RoomService roomService;

    @CrossOrigin(origins = "*")
    @GetMapping("roomlist/{Univid}")
    public RoomlistWrapper list(@PathVariable("Univid") long Univid) {
        RoomlistWrapper roomlist = roomService.getRoomlist(Univid);
        return roomlist;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("roomdetail/{Univid}/{Roomid}")
    public RoomDetailWrapper list(@PathVariable("Univid") long Univid, @PathVariable("Roomid") long Roomid) {
        RoomDetailWrapper roomDetail = roomService.getRoomDetail(Roomid);
        return roomDetail;
    }
}

