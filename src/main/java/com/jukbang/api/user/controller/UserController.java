
package com.jukbang.api.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.room.dto.BoardlistDto;
import com.jukbang.api.user.dto.UserDto;
import com.jukbang.api.community.service.CommunityService;
import com.jukbang.api.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * import com.example.demo.repository.CommentsRepository;
 * import org.springframework.ui.Model;
 **/


@RestController
@AllArgsConstructor
@RequestMapping("userinfo")
public class UserController {


    private UserService userService;
    private CommunityService communityService;


    /**
     * 회원정보 조회 GET
     *
     * @param userid
     * @return (List) userDataList
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{userid}")
    public List getUserDataList(
            @PathVariable("userid") String userid
    ) {
        List<UserDto> userDataList = userService.getUserList(userid);
        return userDataList;
    }


    /**
     * 회원 정보 입력 CREATE
     *
     * @param json
     * @return
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/input")
    public long createUser(
            @RequestBody String json
    ) throws JsonProcessingException {
        return userService.SaveUser(json);

    }


    /**
     * 유저 정보 수정 UPDATE
     *
     * @param id
     * @param json
     * @return (String) success
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public String updateUser(
            @PathVariable("id") long id,
            @RequestBody String json
    ) throws JsonProcessingException {
        userService.rewriteUser(id, json);
        return "success";
    }


    /**
     * 유저 삭제 DELETE
     *
     * @param id
     * @return (String) success
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable("id") long id
    ) {
        userService.deleteUser(id);
        return "success";
    }

    /**
     * 내가 쓴 게시글 목록
     *
     * @param userid
     * @return (List) getMyPosts
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/posts/{userid}")
    public List<BoardlistDto> myPosts(
            @PathVariable("userid") String userid
    ) {
        return communityService.getMyPosts(userid);
    }
}
