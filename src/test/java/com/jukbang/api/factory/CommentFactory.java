package com.jukbang.api.factory;

import com.jukbang.api.community_student.request.CreateCommentRequest;
import com.jukbang.api.community_student.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentFactory {

    @Autowired
    protected CommentsService commentsService;


    public Long generateComment(Long postId, String userId) {
        return commentsService.saveComment(postId,userId,generateCreateCommentRequest());
    }

    public CreateCommentRequest generateCreateCommentRequest(){
        return CreateCommentRequest.builder()
                .body("Testbody")
                .build();
    }
}