package com.jukbang.api.user.controller;

import com.jukbang.api.community.service.CommunityService;
import com.jukbang.api.room.dto.BoardlistDto;
import com.jukbang.api.user.request.CreateUserRequest;
import com.jukbang.api.user.request.UpdateUserRequest;
import com.jukbang.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/userinfo")
public class UserController {

    private final UserService userService;
    private final CommunityService communityService;

    /**
     * 회원정보 조회 GET
     *
     * @param userId
     * @return (List) userDataList
     */
    @GetMapping("/{userId}")
    public List getUserDataList(
            @PathVariable("userId") String userId
    ) {
        return userService.getUserList(userId);
    }

    /**
     * 유저 정보 수정 UPDATE
     *
     * @param id
     * @param updateUserRequest
     * @return (String) success
     */
    @PutMapping("/{id}")
    public String updateUser(
            @PathVariable("id") long id,
            @RequestBody UpdateUserRequest updateUserRequest
    ) {
        String visitorId = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.rewriteUser(id,visitorId, updateUserRequest);
        return "success";
    }

    /**
     * 유저 삭제 DELETE
     *
     * @param id
     * @return (String) success
     */
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
     * @param userId
     * @return (List) getMyPosts
     */
    @GetMapping("/posts/{userId}")
    public List<BoardlistDto> myPosts(
            @PathVariable("userId") String userId
    ) {
        return communityService.getMyPosts(userId);
    }
}
