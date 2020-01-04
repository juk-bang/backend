package com.example.demo.controller;

import com.example.demo.dto.BoardlistDto;
import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class adminController {
    private RoomService roomService;
    @GetMapping("admin/permissionroom/{Univid}")
    public List<Room> list(@PathVariable("Univid") long Univid){
        return roomService.wantpermit(Univid);
    }
    @PostMapping("admin/permissionroom/{Univid}/{no}")
    public void permit(@PathVariable("Univid")long Univid, @PathVariable("no")long no){
        roomService.permit(Univid,no);
    }
    @DeleteMapping("admin/permissionroom/{Univid}/{no}")
    public void notpermit(@PathVariable("Univid")long Univid, @PathVariable("no")long no){
        roomService.dontpermit(Univid,no);
    }
}
