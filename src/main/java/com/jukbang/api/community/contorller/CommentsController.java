package com.jukbang.api.community_student.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.community_student.dto.CommentsDto;
import com.jukbang.api.community_student.request.CreateCommentRequest;
import com.jukbang.api.community_student.request.UpdateCommentRequest;
import com.jukbang.api.community_student.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/community/{role}/{univId}/{postId}/comments")
public class CommentsController {

    private final CommentsService commentsService;

    /**
     * 게시글 별 전체 댓글 리스트 GET
     *
     * @param postId
     * @return
     */
    @GetMapping("")
    public List getCommentsList(
            @PathVariable("postId") Long postId
    ) {
        List<CommentsDto> commentsList = commentsService.getCommentsList(postId);
        return commentsList;
    }

    /**
     * 댓글 CREATE
     *
     * @param postId
     * @param createCommentRequest
     * @return (long) id
     */
    @PostMapping("")
    public long createComment(
            @PathVariable("postId") Long postId,
            @RequestBody CreateCommentRequest createCommentRequest
    ) {

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        return commentsService.saveComment(postId,userId, createCommentRequest);

    }

    /**
     * 댓글 UPDATE
     *
     * @param postId
     * @param commentsId
     * @param updateCommentRequest
     * @return (long) id
     * @throws JsonProcessingException
     */
    @PutMapping("/{commentId}")
    public long updateComment(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentsId,
            @RequestBody UpdateCommentRequest updateCommentRequest
    ) {

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        return commentsService.updateComment(postId,commentsId,userId, updateCommentRequest);
    }

    /**
     * 댓글 DELETE
     *
     * @param postId
     * @return (String) success
     */
    @DeleteMapping("/{commentId}")
    public String deleteComment(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentsId
    ) {

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        commentsService.deleteComment( postId, commentsId, userId);
        return "success";
    }
}
