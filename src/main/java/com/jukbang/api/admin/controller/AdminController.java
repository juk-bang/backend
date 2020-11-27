package com.jukbang.api.admin.controller;

import com.jukbang.api.admin.service.AdminService;
import com.jukbang.api.room.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/admin/permission/")
public class AdminController {

    private final AdminService adminService;

    /**
     * 대학별 방 전체 List GET
     *
     * @return (List) roomList
     */
    @GetMapping("/rooms")
    public List<Room> getRoomList(
    ) {
        return adminService.wantPermit();
    }

    /**
     * 방 게시 permit
     *
     * @param univId 대학가 지역 ID
     * @param roomId 승인할 방 번호
     */
    @PostMapping("/{univId}/{roomId}")
    public void permit(
            @PathVariable("univId") long univId,
            @PathVariable("roomId") long roomId
    ) {
        adminService.permit(univId, roomId);
    }

    /**
     * 방 게시 roomIdt permit
     *
     * @param univId 대학가 지역 ID
     * @param roomId 승인할 방 번호
     */
    @DeleteMapping("/{univId}/{roomId}")
    public void roomIdtPermit(
            @PathVariable("univId") long univId,
            @PathVariable("roomId") long roomId
    ) {
        adminService.reject(univId, roomId);
    }
}
