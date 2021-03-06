
package com.jukbang.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.model.Room;
import com.jukbang.api.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * import com.example.demo.repository.CommentsRepository;
 * import org.springframework.ui.Model;
 **/


@RestController
@AllArgsConstructor
public class FavoriteController {


    private FavoriteService favoriteService;


    /**
     * 찜 조회
     */
    @CrossOrigin(origins = "*")
    @GetMapping("userinfo/favorites/{userid}")
    public List Post(@PathVariable("userid") String userid) {
        List<Room> postdata = favoriteService.getFavoriteList(userid);
        return postdata;
    }


    /**
     * 회원의 찜 목록 추가
     */
    @CrossOrigin(origins = "*")
    @PostMapping("userinfo/favorites/{univid}/{roomid}/{userid}")
    public long write(@PathVariable("univid") int univid, @PathVariable("roomid") int roomid, @PathVariable("userid") String userid) throws JsonProcessingException {
        return favoriteService.SaveFavorite(univid, roomid, userid);

    }


    /**
     * 찜 삭제 기능
     * 찜의 univid, roomid에 접근하여 삭제
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("userinfo/favorites/{univid}/{roomid}/{userid}")
    public String delete(@PathVariable("univid") int univid, @PathVariable("roomid") int roomid, @PathVariable("userid") String userid) {
        favoriteService.DeleteFavorite(univid, roomid, userid);
        return "success";
    }

}
