package com.jukbang.api.community.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.community.dto.CommentsDto;
import com.jukbang.api.community.request.CreateCommentRequest;
import com.jukbang.api.community.request.UpdateCommentRequest;
import com.jukbang.api.community.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/community/comments/{univId}")
public class CommentsController {

    private final CommentsService commentsService;

    /**
     * 게시글 별 전체 댓글 리스트 GET
     *
     * @param univId
     * @param postId
     * @return
     */
    @GetMapping("/{postId}")
    public List getCommentsList(
            @PathVariable("univId") int univId,
            @PathVariable("postId") int postId
    ) {
        List<CommentsDto> commentsList = commentsService.getCommentsList(univId, postId);
        return commentsList;
    }

    /**
     * 댓글 CREATE
     *
     * @param univId
     * @param postId
     * @param createCommentRequest
     * @return (long) id
     */
    @PostMapping("/{postId}")
    public long createComment(
            @PathVariable("univId") int univId,
            @PathVariable("postId") int postId,
            @RequestBody CreateCommentRequest createCommentRequest
    ) {
        return commentsService.SaveComment(univId, postId, createCommentRequest);

    }

    /**
     * 댓글 UPDATE
     *
     * @param univId
     * @param postId
     * @param id
     * @param updateCommentRequest
     * @return (long) id
     * @throws JsonProcessingException
     */
    @PutMapping("/{postId}/{id}")
    public long updateComment(
            @PathVariable("univId") int univId,
            @PathVariable("postId") int postId,
            @PathVariable("id") long id,
            @RequestBody UpdateCommentRequest updateCommentRequest
    ) {
        return commentsService.updateComment(univId, postId, id, updateCommentRequest);
    }

    /**
     * 댓글 DELETE
     *
     * @param id
     * @param postId
     * @return (String) success
     */
    @DeleteMapping("{postId}/{id}")
    public String deleteComment(
            @PathVariable("id") long id,
            @PathVariable("postId") long postId
    ) {
        commentsService.deleteComment(id, postId);
        return "success";
    }
}
