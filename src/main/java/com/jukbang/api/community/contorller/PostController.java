package com.jukbang.api.community.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.community.dto.PostDto;
import com.jukbang.api.community.entity.Post;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.request.UpdatePostRequest;
import com.jukbang.api.community.response.GetPostResponse;
import com.jukbang.api.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/community/{univId}")
public class PostController {

    private final PostService postService;

    /**
     * 대학별 전체 게시글 리스트 GET
     * 불러오기
     *
     * @return postDtoList
     */
    @GetMapping
    public List getPostList() {
        return postService.getPostList();
    }


    /**
     * 각 게시글 GET
     * 불러오기
     *
     * @param postId
     * @return new GetPostResponse()
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{postId}")
    public PostDto getPost(
            @PathVariable("postId") Long postId
    ) {

        return postService.getPost(postId);
    }

    // createPostRequest 할때

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
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        return postService.savePost(univId,userId, createPostRequest);

    }

    /**
     * 게시글 UPDATE
     * 수정하기
     *
     * @param postId
     * @param updatePostRequest
     * @return (Long) id
     * @throws JsonProcessingException
     */
    @PutMapping("/{postId}")
    public Long updatePost(
            @PathVariable("postId") Long postId,
            @RequestBody UpdatePostRequest updatePostRequest
    ) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return postService.rewritePost(postId, userId,updatePostRequest);
    }

    /**
     * 게시글 DELETE
     *
     * @param postId
     * @return (String) success
     */
    @DeleteMapping("/{postId}")
    public String deletePost(
            @PathVariable("postId") Long postId
    ) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        postService.deletePost(postId, userId);
        return "success";
    }
}
