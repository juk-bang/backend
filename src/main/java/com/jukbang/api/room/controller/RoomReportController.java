package com.jukbang.api.room.controller;


import com.jukbang.api.room.request.RoomReportRequest;
import com.jukbang.api.room.service.RoomReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomReportController {

    private final RoomReportService roomReportService;
    /**
     *  신고하기
     */
    @PostMapping("/{roomid}/report")
    public Long reportRoom(
            @PathVariable("roomid") Long roomid,
            @RequestBody RoomReportRequest roomReportRequest
    ){
        return roomReportService.reportRoom(roomid, roomReportRequest);
    }

}
