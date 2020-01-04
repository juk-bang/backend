package com.example.demo.controller;

import com.example.demo.dto.BoardlistDto;
import com.example.demo.dto.CommunityDto;
import com.example.demo.model.Room;
import com.example.demo.service.CommunityService;
import com.example.demo.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ManagerController {
    private RoomService roomService;

    @CrossOrigin(origins="*")
    @GetMapping("manager/manageroom/{Sellerid}")
    public List<Room> write(@PathVariable("Sellerid") long Sellerid) throws JsonProcessingException {
        return roomService.SellerRoomlist(Sellerid);

    }
    @CrossOrigin(origins="*")
    @PostMapping("manager/manageroom/{Sellerid}")
    public Long write(@PathVariable("Sellerid") int Sellerid, @RequestBody String json) throws JsonProcessingException {
        return roomService.SaveRoom(Sellerid, json);

    }
    @CrossOrigin(origins="*")
    @PutMapping("manager/manageroom/{Sellerid}/{Roomid}")
    public Long update(@PathVariable("Sellerid")int Sellerid, @PathVariable("Roomid")Long Roomid, @RequestBody String json) throws JsonProcessingException {
        return  roomService.RewriteRoom(Sellerid,Roomid,json);
    }
    @CrossOrigin(origins="*")
    @DeleteMapping("manager/manageroom/{Sellerid}/{Roomid}")
    public String delete(@PathVariable("Sellerid")int Sellerid, @PathVariable("Roomid")Long Roomid){
        roomService.DeleteRoom(Roomid);
        return "success";
    }
}
