package com.jukbang.api.community;

import com.jukbang.api.common.BaseControllerTest;
import com.jukbang.api.community.request.CreateCommentRequest;
import com.jukbang.api.community.service.CommentsService;
import com.jukbang.api.community.service.CommunityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteComment extends BaseControllerTest {

    @Autowired
    private CommentsService commentsService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("게시글 삭제하기 (성공)")
    void DeleteCommentSuccess() throws Exception {
        CreateCommentRequest createCommentRequest = CreateCommentRequest.builder()
                .id(1)
                .writer("writer")
                .body("body")
                .build();

        commentsService.SaveComment(1,1,createCommentRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/community/comments/{univId}/{postId}/{id}",1,1,1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("DeleteComment"))
        ;
    }
}
