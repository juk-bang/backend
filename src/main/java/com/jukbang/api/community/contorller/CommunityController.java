package com.jukbang.api.community.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.request.UpdatePostRequest;
import com.jukbang.api.community.response.GetPostResponse;
import com.jukbang.api.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/community/{univId}")
public class CommunityController {

    private final CommunityService communityService;

    /**
     * 대학별 전체 게시글 리스트 GET
     * 불러오기
     *
     * @return boardList
     */
    @GetMapping
    public List getPostList() {
        return communityService.getCommunitylist();
    }


    /**
     * 각 게시글 GET
     * 불러오기
     *
     * @param univId
     * @param postId
     * @return new GetPostResponse()
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{postId}")
    public GetPostResponse getPost(
            @PathVariable("univId") int univId,
            @PathVariable("postId") Long postId
    ) {
        return communityService.getPost(univId, postId);
    }


    /**
     * 게시글 CREATE
     * 생성하기
     *
     * @param univId
     * @param createPostRequest
     * @return (Long) id
     */
    @PostMapping("")
    public Long createPost(
            @PathVariable("univId") int univId,
            @RequestBody CreatePostRequest createPostRequest
    ) {

        return communityService.SavePost(univId, createPostRequest);

    }

    /**
     * 게시글 UPDATE
     * 수정하기
     *
     * @param univId
     * @param postId
     * @param updatePostRequest
     * @return (Long) id
     * @throws JsonProcessingException
     */
    @PutMapping("/{postId}")
    public Long updatePost(
            @PathVariable("univId") int univId,
            @PathVariable("postId") Long postId,
            @RequestBody UpdatePostRequest updatePostRequest
    ) {
        return communityService.rewritePost(univId, postId, updatePostRequest);
    }

    /**
     * 게시글 DELETE
     *
     * @param univId
     * @param postId
     * @return (String) success
     */
    @DeleteMapping("/{postId}")
    public String deletePost(
            @PathVariable("univId") int univId,
            @PathVariable("postId") Long postId
    ) {
        communityService.deletePost(postId);
        return "success";
    }
}