package com.example.demo.controller;

import com.example.demo.dto.BoardlistDto;
import com.example.demo.dto.CommunityDto;
import com.example.demo.dto.RoomDetailWrapper;
import com.example.demo.dto.RoomlistWrapper;
import com.example.demo.model.RoomDetail;
import com.example.demo.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
public class RoomController {

    private RoomService roomService;

    @CrossOrigin(origins="*")
    @GetMapping("roomlist/{Univid}")
    public RoomlistWrapper list(@PathVariable("Univid")long Univid){
        RoomlistWrapper  roomlist= roomService.getRoomlist(Univid);
        return roomlist;
    }
    @CrossOrigin(origins="*")
    @GetMapping("roomdetail/{Univid}/{Roomid}")
    public RoomDetailWrapper list(@PathVariable("Univid")long Univid, @PathVariable("Roomid")long Roomid){
        RoomDetailWrapper  roomDetail= roomService.getRoomDetail(Roomid);
        return roomDetail;
    }
}

