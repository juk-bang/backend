package com.jukbang.api.community_all;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community_student.request.UpdateCommentRequest;
import com.jukbang.api.community_student.service.CommentsService;
import com.jukbang.api.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateComment extends BaseControllerTest {

    @Autowired
    private CommentsService commentsService;

    @Test
    @DisplayName("커뮤니티(전체)_댓글 수정하기 (성공)")
    void UpdateCommentSuccess() throws Exception {
        String accessToken = userFactory.signUpUser(1, UserRole.ROLE_LANDLORD).getAccessToken();


        Long postId = postFactoryAll.generatePost(1,"TestUser1");

        Long commentId = commentFactory.generateComment(postId,"TestUser1");

        UpdateCommentRequest updateCommentRequest = UpdateCommentRequest.builder()
                .body("TestBody_2")
                .build();

        commentsService.updateComment(postId,commentId,"TestUser1",updateCommentRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/community/{role}/{univId}/{postId}/comments/{commentId}","all",1,postId,commentId)
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
