package com.jukbang.api.room.controller;

import com.jukbang.api.room.entity.Room;
import com.jukbang.api.room.request.CreateRoomRequest;
import com.jukbang.api.room.request.UpdateRoomRequest;
import com.jukbang.api.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager/manageroom/{sellerId}")
public class ManagerController {

    private final RoomService roomService;

    /**
     * 판매자 방 list GET
     *
     * @param sellerId
     * @return (List) SellerRoomList
     */
    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Room> getSellerRoomList(
            @PathVariable("sellerId") String sellerId
    ) {
        return roomService.SellerRoomlist(sellerId);
    }

    /**
     * 판매자 방 추가하기
     *
     * @param sellerId
     * @param createRoomRequest
     * @return (Long) id
     */
    @CrossOrigin(origins = "*")
    @PostMapping
    public Long createSellerRoom(
            @PathVariable("sellerId") String sellerId,
            @RequestBody CreateRoomRequest createRoomRequest
    ) {
        return roomService.createRoom(sellerId, createRoomRequest);
    }

    /**
     * 판매자 방 수정 UPDATE
     *
     * @param sellerId
     * @param roomId
     * @param updateRoomRequest
     * @return (Long) id
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{roomId}")
    public Long updateRoom(
            @PathVariable("sellerId") String sellerId,
            @PathVariable("roomId") Long roomId,
            @RequestBody UpdateRoomRequest updateRoomRequest
    ) {
        return roomService.updateRoom(sellerId, roomId, updateRoomRequest);
    }

    /**
     * 판매자 방 삭제 DELETE
     *
     * @param sellerId
     * @param roomId
     * @return (String) success
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{roomId}")
    public String deleteRoom(
            @PathVariable("sellerId") String sellerId,
            @PathVariable("roomId") Long roomId
    ) {
        roomService.deleteRoom(roomId);
        return "success";
    }
}
