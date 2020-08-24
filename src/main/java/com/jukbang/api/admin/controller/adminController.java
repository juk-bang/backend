package com.jukbang.api.admin.controller;

import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class adminController {
    private RoomService roomService;

    @CrossOrigin(origins = "*")
    @GetMapping("admin/permissionroom/{Univid}")
    public List<Room> list(@PathVariable("Univid") long Univid) {
        return roomService.wantpermit(Univid);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("admin/permissionroom/{Univid}/{no}")
    public void permit(@PathVariable("Univid") long Univid, @PathVariable("no") long no) {
        roomService.permit(Univid, no);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("admin/permissionroom/{Univid}/{no}")
    public void notpermit(@PathVariable("Univid") long Univid, @PathVariable("no") long no) {
        roomService.dontpermit(Univid, no);
    }
}
