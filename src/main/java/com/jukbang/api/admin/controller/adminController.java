package com.jukbang.api.admin.controller;

import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("admin/permissionroom/{Univid}")
public class adminController {
    private RoomService roomService;

    /**
     * 대학별 방 전체 List GET
     *
     * @param univid
     * @return (List) roomList
     */
    @CrossOrigin(origins = "*")
    @GetMapping("")
    public List<Room> getRoomList(
            @PathVariable("univid") long univid
    ) {
        return roomService.wantpermit(univid);
    }

    /**
     * 방 게시 permit
     *
     * @param univid
     * @param no
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/{yes}")
    public void permit(
            @PathVariable("univid") long univid,
            @PathVariable("no") long no
    ) {
        roomService.permit(univid, no);
    }

    /**
     * 방 게시 not permit
     *
     * @param Univid
     * @param no
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{no}")
    public void notPermit(
            @PathVariable("Univid") long univid,
            @PathVariable("no") long no
    ) {
        roomService.dontpermit(univid, no);
    }
}
