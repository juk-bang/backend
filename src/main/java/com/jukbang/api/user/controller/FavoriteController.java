package com.jukbang.api.user.controller;

import com.jukbang.api.user.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    /**
     * favorite list 조회 GET
     *
     * @param userId
     * @return (List) favoriteList
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/userinfo/favorites/{userId}")
    public List getFavoriteList(
            @PathVariable("userId") String userId
    ) {
        return favoriteService.getFavoriteList(userId);
    }

    /**
     * 찜 목록 추가 CREATE
     *
     * @param univId
     * @param roomId
     * @param userId
     * @return (long) id
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/userinfo/favorites/{univId}/{roomId}/{userId}")
    public long createFavorite(
            @PathVariable("univId") int univId,
            @PathVariable("roomId") int roomId,
            @PathVariable("userId") String userId
    ) {
        return favoriteService.SaveFavorite(univId, roomId, userId);
    }

    /**
     * 찜 삭제 DELETE
     *
     * @param univId
     * @param roomId
     * @param userId
     * @return (String) success
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/userinfo/favorites/{univId}/{roomId}/{userId}")
    public String deleteFavorite(
            @PathVariable("univId") int univId,
            @PathVariable("roomId") int roomId,
            @PathVariable("userId") String userId
    ) {
        favoriteService.DeleteFavorite(univId, roomId, userId);
        return "success";
    }
}
