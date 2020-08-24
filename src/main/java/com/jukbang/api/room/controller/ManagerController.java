package com.jukbang.api.room.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ManagerController {
    private RoomService roomService;

    @CrossOrigin(origins = "*")
    @GetMapping("manager/manageroom/{Sellerid}")
    public List<Room> write(@PathVariable("Sellerid") long Sellerid) throws JsonProcessingException {
        return roomService.SellerRoomlist(Sellerid);

    }

    @CrossOrigin(origins = "*")
    @PostMapping("manager/manageroom/{Sellerid}")
    public Long write(@PathVariable("Sellerid") String Sellerid, @RequestBody String json) throws JsonProcessingException {
        return roomService.SaveRoom(Sellerid, json);

    }

    @CrossOrigin(origins = "*")
    @PutMapping("manager/manageroom/{Sellerid}/{Roomid}")
    public Long update(@PathVariable("Sellerid") String Sellerid, @PathVariable("Roomid") Long Roomid, @RequestBody String json) throws JsonProcessingException {
        return roomService.RewriteRoom(Sellerid, Roomid, json);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("manager/manageroom/{Sellerid}/{Roomid}")
    public String delete(@PathVariable("Sellerid") int Sellerid, @PathVariable("Roomid") Long Roomid) {
        roomService.DeleteRoom(Roomid);
        return "success";
    }
}
