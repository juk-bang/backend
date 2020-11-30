package com.jukbang.api.user.controller;

import com.jukbang.api.community.dto.MyPostListDto;
import com.jukbang.api.community.dto.PostListDto;
import com.jukbang.api.community.service.PostService;
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
    private final PostService postService;

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
     * @param updateUserRequest
     * @return (String) success
     */
    @PutMapping
    public String updateUser(
            @RequestBody UpdateUserRequest updateUserRequest
    ) {
        String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.rewriteUser(requestUserId, updateUserRequest);
        return "success";
    }

    /**
     * 유저 삭제 DELETE
     *
     * @return (String) success
     */
    @DeleteMapping
    public String deleteUser(
    ) {
        String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteUser(requestUserId);
        return "success";
    }

/*    *//**
     * 내가 쓴 게시글 목록
     *
     * @param userId
     * @return (List) getMyPosts
     */
    @GetMapping("/posts/{userId}")
    public List<MyPostListDto> myPosts(
            @PathVariable("userId") String userId
    ) {
        return postService.getMyPosts(userId);
    }
}
