package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreateCommentRequest;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.request.UpdateCommentRequest;
import com.jukbang.api.community.service.CommentsService;
import com.jukbang.api.community.service.PostService;
import com.jukbang.api.user.UserRole;
import com.jukbang.api.user.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateComment extends BaseControllerTest {

    @Autowired
    private CommentsService commentsService;

    @Test
    @DisplayName("댓글 수정하기 (성공)")
    void UpdateCommentSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_STUDENT).getAccessToken();


        Long postId = postFactory.generatePost(1,"TestUser1");

        Long commentId = commentFactory.generateComment(postId,"TestUser1");

        UpdateCommentRequest updateCommentRequest = UpdateCommentRequest.builder()
                .body("TestBody_2")
                .build();

        commentsService.updateComment(postId,commentId,"TestUser1",updateCommentRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/community/{univId}/{postId}/comments/{commentId}",1,postId,commentId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .content(this.objectMapper.writeValueAsString(updateCommentRequest)
                )
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("UpdateComment"))
        ;

    }


}
