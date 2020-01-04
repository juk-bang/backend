
package com.example.demo.controller;

import com.example.demo.dto.BoardlistDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Community;
import com.example.demo.service.CommunityService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 import com.example.demo.repository.CommentsRepository;
 import org.springframework.ui.Model;
 **/


@RestController
@AllArgsConstructor
public class UserController {


    private UserService userService;
    private CommunityService communityService;


    /**
     *  회원 정보 조회
     */
    @CrossOrigin(origins = "*")
    @GetMapping("userinfo/{userid}")
    public List Post(@PathVariable("userid")String userid){
        List<UserDto> postdata = userService.getUserList(userid);
        return postdata;
    }


    /**
     *  회원정보 임의 입력
     */
    @CrossOrigin(origins = "*")
    @PostMapping("userinfo/input")
    public long write(@RequestBody String json) throws JsonProcessingException {
        return userService.SaveUser(json);

    }


    /**
     *  회원정보 수정 기능
     *  회원의 고유번호 (id) 에 접근하여 수정
     */
    @CrossOrigin(origins = "*")
    @PutMapping("userinfo/{id}")
    public String update(@PathVariable("id")long id, @RequestBody String json) throws JsonProcessingException {
        userService.rewriteUser(id,json);
        return "success";
    }


    /**
     *  회원 삭제 기능
     *  댓글의 고유번호 (id) 에 접근하여 삭제
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("userinfo/{id}")
    public String delete(@PathVariable("id")long id){
        userService.deleteUser(id);
        return "success";
    }
    /**
     *  내가 쓴 게시글 목록
     */
    @CrossOrigin(origins = "*")
    @GetMapping("userinfo/posts/{userid}")
    public List<BoardlistDto> myPosts(@PathVariable("userid")String userid){
        return communityService.getMyPosts(userid);
    }
}
