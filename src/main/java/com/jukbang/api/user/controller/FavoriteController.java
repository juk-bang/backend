
package com.jukbang.api.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.room.entity.Room;
import com.jukbang.api.user.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * import com.example.demo.repository.CommentsRepository;
 * import org.springframework.ui.Model;
 **/


@RestController
@AllArgsConstructor
@RequestMapping("userinfo/favorites/{userid}")
public class FavoriteController {


    private FavoriteService favoriteService;


    /**
     * favorite list 조회 GET
     *
     * @param userid
     * @return (List) favoriteList
     */
    @CrossOrigin(origins = "*")
    @GetMapping("")
    public List getFavoriteList(
            @PathVariable("userid") String userid
    ) {
        List<Room> favoriteList = favoriteService.getFavoriteList(userid);
        return favoriteList;
    }


    /**
     *  찜 목록 추가 CREATE
     *
     * @param univid
     * @param roomid
     * @param userid
     * @return (long) id
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/{roomid}/{userid}")
    public long createFavorite(
            @PathVariable("univid") int univid,
            @PathVariable("roomid") int roomid,
            @PathVariable("userid") String userid
    ) throws JsonProcessingException {
        return favoriteService.SaveFavorite(univid, roomid, userid);

    }


    /**
     * 찜 삭제 DELETE
     *
     * @param univid
     * @param roomid
     * @param userid
     * @return (String) success
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{roomid}/{userid}")
    public String deleteFavorite(
            @PathVariable("univid") int univid,
            @PathVariable("roomid") int roomid,
            @PathVariable("userid") String userid
    ) {
        favoriteService.DeleteFavorite(univid, roomid, userid);
        return "success";
    }

}
