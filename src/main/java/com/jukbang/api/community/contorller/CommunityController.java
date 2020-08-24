package com.jukbang.api.community.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.community.response.GetPostResponse;
import com.jukbang.api.room.dto.BoardlistDto;
import com.jukbang.api.community.dto.CommunityDto;
import com.jukbang.api.community.service.CommunityService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value= "community/{univid}")
public class CommunityController {


    private CommunityService communityService;

    /**
     *  대학별 전체 게시글 리스트 GET
     *  불러오기
     *
     * @param model
     * @return boardList
     *
     *
     */
    @CrossOrigin(origins = "*")
    @GetMapping("")
    public List getPostList(Model model) {
        List<BoardlistDto> boardList = communityService.getCommunitylist();
        return boardList;
    }


    /**
     *  각 게시글 GET
     *  불러오기
     *
     * @param univid
     * @param postid
     * @return new GetPostResponse()
     *
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{postid}")
    public GetPostResponse getPost (
            @PathVariable("univid") int univid,
            @PathVariable("postid") Long postid
    ) {
        return new GetPostResponse();
    }


    /**
     *  게시글 CREATE
     *  생성하기
     *
     * @param univid
     * @param json
     * @return (Long) id
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PostMapping("")
    public Long createPost(
            @PathVariable("univid") int univid,
            @RequestBody String json
    ) throws JsonProcessingException {

        return communityService.SavePost(univid, json);

    }

    /**
     *  게시글 UPDATE
     *  수정하기
     *
     * @param univid
     * @param postid
     * @param json
     * @return (Long) id
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/{postid}")
    public Long updatePost(
            @PathVariable("univid") int univid,
            @PathVariable("postid") Long postid,
            @RequestBody String json
    ) throws JsonProcessingException {
        return communityService.rewritePost(univid, postid, json);
    }


    /**
     * 게시글 DELETE
     *
     * @param univid
     * @param postid
     * @return (String) success
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{postid}")
    public String deletePost(
            @PathVariable("univid") int univid,
            @PathVariable("postid") Long postid
    ) {
        communityService.deletePost(postid);
        return "success";
    }

}
