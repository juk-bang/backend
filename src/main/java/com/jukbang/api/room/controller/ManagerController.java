package com.jukbang.api.room.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("manager/manageroom/{Sellerid}")
public class ManagerController {
    private RoomService roomService;

    /**
     * 판매자 방 list GET
     *
     * @param Sellerid
     * @return (List) SellerRoomList
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @GetMapping("")
    public List<Room> getSellerRoomList(
            @PathVariable("sellerid") long sellerid
    ) throws JsonProcessingException {
        return roomService.SellerRoomlist(sellerid);

    }

    /**
     * 판매자 방 추가하기
     *
     * @param Sellerid
     * @param json
     * @return (Long) id
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PostMapping("")
    public Long createSellerRoom(
            @PathVariable("sellerid") String sellerid,
            @RequestBody String json
    ) throws JsonProcessingException {
        return roomService.createRoom(sellerid, json);

    }

    /**
     * 판매자 방 수정 UPDATE
     *
     * @param Sellerid
     * @param Roomid
     * @param json
     * @return (Long) id
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{roomid}")
    public Long updateRoom(
            @PathVariable("sellerid") String sellerid,
            @PathVariable("roomid") Long roomid,
            @RequestBody String json
    ) throws JsonProcessingException {
        return roomService.updateRoom(sellerid, roomid, json);
    }

    /**
     * 판매자 방 삭제 DELETE
     *
     * @param Sellerid
     * @param Roomid
     * @return (String) success
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{roomid}")
    public String deleteRoom(
            @PathVariable("sellerid") int sellerid,
            @PathVariable("roomid") Long roomid) {
        roomService.deleteRoom(roomid);
        return "success";
    }
}
